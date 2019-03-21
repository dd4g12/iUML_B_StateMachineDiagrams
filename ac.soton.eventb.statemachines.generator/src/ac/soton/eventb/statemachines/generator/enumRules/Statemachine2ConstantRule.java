package ac.soton.eventb.statemachines.generator.enumRules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eventb.emf.core.EventBNamedCommentedComponentElement;
import org.eventb.emf.core.context.Constant;
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

public class Statemachine2ConstantRule extends AbstractEventBGeneratorRule implements IRule{

	private Statemachine rootStatemachine = null;
	
	/**
	 * Only enabled for enumeration translation
	 */
	@Override
	public boolean enabled(EObject sourceElement) throws Exception  {
		Statemachine sourceSM = (Statemachine) sourceElement;
		rootStatemachine = Utils.getRootStatemachine(sourceSM);
		return rootStatemachine.getTranslation().equals(TranslationKind.SINGLEVAR) &&
				sourceSM.getRefines() == null && (Utils.hasParentState(sourceSM) || Utils.hasFinalState(sourceSM));
	}

	/**
	 * Waits until context has not being generated
	 */
	@Override
	public boolean dependenciesOK(EObject sourceElement, final List<TranslationDescriptor> generatedElements) throws Exception  {
		EventBNamedCommentedComponentElement container = (EventBNamedCommentedComponentElement)EcoreUtil.getRootContainer(sourceElement);
		
		for(Context ctx : ((Machine)container).getSees())
			if(ctx.getName().equals(Strings.CTX_NAME(rootStatemachine)))
				return true;
		
		return Find.generatedElement(generatedElements, Find.project(container), components, Strings.CTX_NAME(rootStatemachine)) != null;
	}
	
	/**
	 * Generates the NULL constants (to state that the state is not active)
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		EventBNamedCommentedComponentElement container = (EventBNamedCommentedComponentElement)EcoreUtil.getRootContainer(sourceElement);
		
		Statemachine sourceSM = (Statemachine) sourceElement;
		Context ctx = (Context)Find.generatedElement(generatedElements, Find.project(container), components, Strings.CTX_NAME(rootStatemachine));
		
		if(ctx == null){
			for(Context ictx : ((Machine)container).getSees())
				if(ictx.getName().equals(Strings.CTX_NAME(rootStatemachine))){
					ctx = ictx;
					break;
				}
		}
		
		Constant newConstant = (Constant) Make.constant(sourceSM.getName() + Strings._NULL, "");
		
		ret.add(Make.descriptor(ctx, constants, newConstant, 1));
		return ret;
	}

}
