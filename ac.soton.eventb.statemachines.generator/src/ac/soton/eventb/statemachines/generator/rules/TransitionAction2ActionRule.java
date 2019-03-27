package ac.soton.eventb.statemachines.generator.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eventb.emf.core.machine.Action;
import org.eventb.emf.core.machine.Event;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.Transition;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class TransitionAction2ActionRule extends AbstractEventBGeneratorRule  implements IRule {
	

	@Override
	public boolean dependenciesOK(EObject sourceElement, final List<TranslationDescriptor> generatedElements) throws Exception  {
		return true;
	
	}

	/**
	 * TrasitionAction2Action
	 * 
	 * Generates actions from transition actions
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
	
		Transition sourceTransition = (Transition) sourceElement;
		List<Action> generatedActions = generateActions(sourceTransition);
		
		
		for(Event ev : sourceTransition.getElaborates()){
			if(!ev.getName().equals(Utils.INITIALISATION_EVENT_NAME)){
				for(Action a : generatedActions){
					ret.add(Make.descriptor(ev, actions, Make.action(a.getName(), a.getAction(), a.getComment()), 10));
				}				
				
			}
		}
			
		return ret;		
	} 



	/**
	 * Calculate the actions from a given Transition
	 * @param sourceTransition
	 * @return
	 */
	private List<Action> generateActions(Transition sourceTransition){
		List<Action> ret = new ArrayList<Action>();
		
		for(Action a : sourceTransition.getActions()){
			ret.add(transitionAction2Action(a));
		}	
		return ret;
	}
	
	
	
	/**
	 * Generates an action from another action
	 * @param a
	 * @return
	 */
	private Action transitionAction2Action(Action a){
		return a;
	}
	
	
	
}