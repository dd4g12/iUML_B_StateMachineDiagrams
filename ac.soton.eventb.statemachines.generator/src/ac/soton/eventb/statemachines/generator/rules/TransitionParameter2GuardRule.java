package ac.soton.eventb.statemachines.generator.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Guard;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.emf.core.extension.coreextension.TypedParameter;
import ac.soton.eventb.statemachines.Transition;
import ac.soton.eventb.statemachines.generator.strings.Strings;

public class TransitionParameter2GuardRule extends AbstractEventBGeneratorRule  implements IRule {

	/**
	 * TransitionGuard2Guard
	 * 
	 * Adds the transition guard to all events the transition elaborates
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		Transition sourceTransition = (Transition) sourceElement;

		for(TypedParameter p : sourceTransition.getParameters()){
			
			Guard grd = generateTransitionGuardFromTypedParameter(p);
			for(Event e : sourceTransition.getElaborates()){
				ret.add(Make.descriptor(e, guards, Make.guard(grd.getName(), grd.getPredicate()), 2));
			}
			
		}	
		return ret;
	}
	
	
	/**
	 * Generate a guard from a given predicate
	 * @param p
	 * @return
	 */
	private Guard generateTransitionGuardFromTypedParameter(TypedParameter p){
		return (Guard) Make.guard(p.getName() + Strings._TYPE, 
				p.getName() + Strings.B_IN + p.getType());
	}
}
