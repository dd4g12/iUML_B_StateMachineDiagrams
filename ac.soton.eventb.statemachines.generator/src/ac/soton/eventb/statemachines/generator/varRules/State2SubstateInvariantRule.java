package ac.soton.eventb.statemachines.generator.varRules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eventb.emf.core.EventBNamedCommentedComponentElement;
import org.eventb.emf.core.machine.Invariant;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.State;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.TranslationKind;
import ac.soton.eventb.statemachines.generator.strings.Strings;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class State2SubstateInvariantRule extends AbstractEventBGeneratorRule  implements IRule {

	@Override
	public boolean enabled(EObject sourceElement) throws Exception{
		State sourceState = (State) sourceElement;
		Statemachine rootSM = Utils.getRootStatemachine(sourceState);
		return rootSM.getTranslation().equals(TranslationKind.MULTIVAR) && //Variables translation only
				rootSM.getInstances() == null && //NON-LIFTED STATEMACHINE
				((Utils.getSuperState(sourceState)!=null ) && //Has a superState i.e. not a state from the root statemachine
				sourceState.getRefines() == null); //not a refined state
	}
	
	
	/**
	 * States2SubstateInvariants
	 * Generates a new substate invariant for a state.
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		EventBNamedCommentedComponentElement container = (EventBNamedCommentedComponentElement)EcoreUtil.getRootContainer(sourceElement);
		State sourceState = (State)sourceElement;
	
		State parentState = Utils.getSuperState(sourceState);
		Invariant newInvariant = Make.invariant(sourceState.getName() + Strings._SUBSTATEOF_ + parentState.getName(),
				Utils.parenthesize(sourceState.getName() + Strings.B_EQ + Strings.B_TRUE) + Strings.B_IMPL + Utils.parenthesize(parentState.getName() + Strings.B_EQ + Strings.B_TRUE), "");
		ret.add(Make.descriptor(container, invariants, newInvariant, 3));

		return ret;
		
	}

	
}