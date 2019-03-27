package ac.soton.eventb.statemachines.generator.enumRules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eventb.emf.core.context.Context;
import org.eventb.emf.core.machine.Machine;

import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.utils.Find;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.TranslationKind;
import ac.soton.eventb.statemachines.generator.strings.Strings;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class RemoveRedundantContextsRule extends AbstractEventBGeneratorRule implements IRule{

	private Machine machine;
	private Context implicitContext;
	
	/**
	 * Only enabled for enumeration translation
	 */
	@Override
	public boolean enabled(EObject sourceElement) throws Exception  {
		machine = (Machine) Utils.getTranslationTarget();
		return Utils.isRootStatemachine((Statemachine)sourceElement) &&
				((Statemachine) sourceElement).getTranslation().equals(TranslationKind.SINGLEVAR) &&
				machine.getRefines().size() > 0; //Just in refinements, when contexts get extended
	}

	/**
	 * Fire late so we can be sure the context extends field has already been completely populated
	 */
	@Override
	public boolean fireLate(){
		return true;
	}
	
	/**
	 * Verifies if the context has already been generated. Also verifies if the newly generated context
	 * is already extending the abstract one
	 */
	@Override
	public boolean dependenciesOK(EObject sourceElement, final List<TranslationDescriptor> generatedElements) throws Exception  {
		implicitContext = (Context) Find.generatedElement(generatedElements, Find.project(machine), components, Strings.CTX_NAME(machine, (Statemachine)sourceElement));
		if(implicitContext == null){
			implicitContext = (Context) Find.named(machine.getSees(), Strings.CTX_NAME(machine, (Statemachine)sourceElement));
		}
		return implicitContext != null;
	}

	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();						
		List<Context> redundantCtxs = findRedundantCtx(implicitContext);
		for(Context ctx : redundantCtxs){
			if(Find.named(machine.getSees(), ctx.getName()) != null){
				ret.add(Make.descriptor(machine, sees, ctx, true));
			}
		}
		return ret;
	}

	private List<Context> findRedundantCtx(Context ctx) {
		List<Context> redundant = new ArrayList<Context>();
		redundant.addAll(ctx.getExtends());
		for(Context ictx : ctx.getExtends()){
			System.out.println(ictx.getName());
			redundant.addAll(findRedundantCtx(ictx));
		}
		return redundant;
	}

}

