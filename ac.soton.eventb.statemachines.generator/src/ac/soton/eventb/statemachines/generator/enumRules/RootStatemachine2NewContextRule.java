package ac.soton.eventb.statemachines.generator.enumRules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eventb.emf.core.context.Context;
import org.eventb.emf.core.machine.Machine;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Find;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.TranslationKind;
import ac.soton.eventb.statemachines.generator.strings.Strings;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class RootStatemachine2NewContextRule extends AbstractEventBGeneratorRule implements IRule{
	
	
	
	@Override
	public boolean enabled(EObject sourceElement) throws Exception  {	
		//Machine container = (Machine)EcoreUtil.getRootContainer(sourceElement);
		return Utils.isRootStatemachine((Statemachine)sourceElement) &&
				((Statemachine) sourceElement).getTranslation().equals(TranslationKind.SINGLEVAR); //&& 
				//!hasImplicitContext(container);
				
	
	}
	/**
	 * Generates the implicit context
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		
		Machine container = (Machine)EcoreUtil.getRootContainer(sourceElement);

		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
	
		Context implicitContext =  (Context) Make.context(container.getName() + Strings._IMPLICIT_CONTEXT, "");
		
		ret.add(Make.descriptor(Find.project(container), components,implicitContext ,1));
		ret.add(Make.descriptor(container, seesNames, implicitContext.getName(), 1));

		for(Context abstractContext : getGeneratedAbstractContext(container)){
			if(!implicitContext.getExtendsNames().contains(abstractContext.getName())){
				implicitContext.getExtendsNames().add(abstractContext.getName());
			}
		}

		return ret;

	}

	/**
	 * this returns the abstract seen implicit contexts
	 * i.e. those that are named in the way we name implicit contexts
	 * @param machine
	 * @return
	 */
	//TODO:  
	private List<Context> getGeneratedAbstractContext(Machine machine){
		List<Context> abstractCtxs = new ArrayList<Context>();
		for(Machine abstractMachine : machine.getRefines()){
			for(Context ctx : abstractMachine.getSees()){
				if(ctx.getName().equals(abstractMachine.getName() + Strings._IMPLICIT_CONTEXT)){
					abstractCtxs.add(ctx);
				}
			}
		}
		return abstractCtxs;
	}
	
}



	
	
	
