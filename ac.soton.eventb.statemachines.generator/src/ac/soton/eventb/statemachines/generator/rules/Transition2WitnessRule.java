package ac.soton.eventb.statemachines.generator.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Witness;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.Transition;
import ac.soton.eventb.statemachines.generator.strings.Strings;


/**
 * Works for both kinds of translations
 * @author matheus
 *
 */

public class Transition2WitnessRule extends AbstractEventBGeneratorRule  implements IRule {
	


	/**
	 * Trasition2Witness
	 * 
	 * Generates the witness for events elaborated by the transition
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
	
		Transition sourceTransition = (Transition) sourceElement;
		
		for(Event ev : sourceTransition.getElaborates()){
			if(!ev.getName().equals(Strings.INIT)){
				List<Witness> generatedWitness = generateWitness(sourceTransition, ev);
				for(Witness w : generatedWitness){
					ret.add(Make.descriptor(ev, witnesses, Make.witness(w.getName(), w.getPredicate(), w.getComment()), 10));
				}				
				
			}
		}
			
		return ret;		
	}
	
	/**
	 * Generate the witnesses for a given event from a transition which elaborates it.
	 * @param t
	 * @param event
	 * @return
	 */
	private List<Witness> generateWitness(Transition t, Event event){
		List<Witness> ret = new ArrayList<Witness>();		
		for(Witness w : t.getWitnesses()){
			ret.add(transitionWitness2witness(w));
		}		
		return ret;
	}
	
	/**
	 * Creates a witness from the Transition witness
	 * @param w
	 * @return
	 */
	private Witness transitionWitness2witness(Witness w){
		return w;
	}
	
	
}
