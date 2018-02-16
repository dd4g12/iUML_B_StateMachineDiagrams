package ac.soton.eventb.statemachines.generator.enumRules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eventb.emf.core.EventBNamedCommentedComponentElement;
import org.eventb.emf.core.context.Axiom;
import org.eventb.emf.core.context.Context;
import org.eventb.emf.core.machine.Machine;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Find;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.State;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.TranslationKind;
import ac.soton.eventb.statemachines.generator.strings.Strings;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class State2TypingAxiomRule extends AbstractEventBGeneratorRule implements IRule{

	private Statemachine rootStatemachine = null;
	
	/**
	 * Only enabled for enumeration translation
	 */
	@Override
	public boolean enabled(EObject sourceElement) throws Exception  {
		rootStatemachine =  Utils.getRootStatemachine((State) sourceElement);
		return rootStatemachine.getTranslation().equals(TranslationKind.SINGLEVAR) &&
				((State)sourceElement).getRefines() == null;
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
	 * Generates typing axioms from a State
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		EventBNamedCommentedComponentElement container = (EventBNamedCommentedComponentElement)EcoreUtil.getRootContainer(sourceElement);
		
		State sourceState = (State) sourceElement;
		Context ctx = (Context)Find.generatedElement(generatedElements, Find.project(container), components, Strings.CTX_NAME(rootStatemachine));
		
		if(ctx == null){
			for(Context ictx : ((Machine)container).getSees())
				if(ictx.getName().equals(Strings.CTX_NAME(rootStatemachine))){
					ctx = ictx;
					break;
				}
		}
		
		Axiom newSet = (Axiom) Make.axiom(Strings.TYPEOF_ + sourceState.getName(),
				sourceState.getName() + Strings.B_IN + Utils.getStatemachine(sourceState).getName() + Strings._STATES,
				"");
		
		ret.add(Make.descriptor(ctx, axioms, newSet, 9));
		return ret;
	}
	
}
