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
import ac.soton.eventb.statemachines.Transition;

public class TransitionGuard2GuardRule extends AbstractEventBGeneratorRule  implements IRule {

	/**
	 * TransitionGuard2Guard
	 * 
	 * Adds the transition guard to all events the transition elaborates
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		Transition sourceTransition = (Transition) sourceElement;

		for(Event e : sourceTransition.getElaborates()){
			for(Guard grd : sourceTransition.getGuards()){
				ret.add(Make.descriptor(e, guards, Make.guard(grd.getName(), grd.isTheorem(), grd.getPredicate(), grd.getComment()), 10));
			}
		}	
		return ret;
	}
}
