package ac.soton.eventb.statemachines.generator.varRules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eventb.emf.core.machine.Invariant;
import org.eventb.emf.core.machine.Machine;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.State;
import ac.soton.eventb.statemachines.TranslationKind;
import ac.soton.eventb.statemachines.generator.strings.Strings;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class State2TypingInvariantsRule extends AbstractEventBGeneratorRule  implements IRule  {
	
	/**
	 * Rule not to be applied on lifted statemachines
	 */
	@Override
	public boolean enabled(EObject sourceElement) throws Exception{
		State sourceState = (State) sourceElement;
		return Utils.getRootStatemachine(sourceState).getTranslation().equals(TranslationKind.MULTIVAR) &&
				(sourceState.getRefines() == null) && 
				Utils.getRootStatemachine(sourceState).getInstances() == null; //If it is not a state from the root statemachine
	}
	
	/**
	 * Generates a new variable named as the states it represents
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		State sourceState = (State) sourceElement;
		Machine machine = (Machine) Utils.getTranslationTarget();;
		Invariant newInvariant = Make.invariant(Strings.TYPEOF_ + sourceState.getName(), sourceState.getName()+Strings.B_IN+Strings.B_BOOL, "");
		ret.add(Make.descriptor(machine, invariants, newInvariant, 1));
		return ret;
	}

}
