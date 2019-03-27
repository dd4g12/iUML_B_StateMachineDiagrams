package ac.soton.eventb.statemachines.generator.varRules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eventb.emf.core.EventBNamedCommentedComponentElement;
import org.eventb.emf.core.machine.Invariant;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.AbstractNode;
import ac.soton.eventb.statemachines.State;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.TranslationKind;
import ac.soton.eventb.statemachines.generator.strings.Strings;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class LiftedRootStatemachine2TypingInvariantRule extends AbstractEventBGeneratorRule  implements IRule  {
	
	/**
	 * Rule to be applied on lifted statemachines
	 */
	@Override
	public boolean enabled(EObject sourceElement) throws Exception{
		Statemachine sourceSM = (Statemachine) sourceElement;
		return Utils.getRootStatemachine(sourceSM).getTranslation().equals(TranslationKind.MULTIVAR)
				&& (Utils.isRootStatemachine(sourceSM)) 
				&& sourceSM.getInstances() != null;
	}

	
	/**
	 * 
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		Statemachine rootSM = (Statemachine) sourceElement;
		
		EventBNamedCommentedComponentElement container = Utils.getTranslationTarget();
	
		List<Invariant> newInvariants = statemachine2typeInvariant(rootSM);
		for(Invariant inv : newInvariants){
			ret.add(Make.descriptor(container, invariants, inv, 1));
		}
		
		return ret;
	}
	
	
	/**
	 * Generate all the invariants for a statemachine
	 * @param sm
	 * @return
	 */
	private List<Invariant> statemachine2typeInvariant(Statemachine sm) {
		List<Invariant> ret = new ArrayList<Invariant>();
		
		for(AbstractNode abs : sm.getNodes()){
			if(abs instanceof State)
				ret.addAll(state2typeInvariants((State)abs));
		}
		return ret;
	}

	
	private List<Invariant> state2typeInvariants(State s){
		List<Invariant> ret = new ArrayList<Invariant>();
		
		//XXX The order of these calls is important. This makes sure invariants
		//are added in the right order. They must be generated in the order
		//of dependency (i.e. state and then sub-states

		//Only generate refinements for non-refined states
		if(s.getRefines() == null)
			ret.add(generateInvariantFromState(s));
		
		for(Statemachine sm : s.getStatemachines())
			ret.addAll(statemachine2typeInvariant(sm));
		

		return ret;
	}

	
	
	
	private Invariant generateInvariantFromState(State s){
		return (Invariant) Make.invariant(Strings.TYPEOF_ + s.getName(),
				generatePredicate(s), "");
	}
	
	/**
	 * Calculates the predicate needed for the given State
	 * 
	 * @param sourceState
	 * @return
	 */
	private String generatePredicate (State sourceState){
			if(Utils.isRootStatemachine(Utils.getStatemachine(sourceState)))
				return sourceState.getName() + Strings.B_SUBSETEQ + Utils.getRootStatemachine(sourceState).getInstances().getName();
			else
				return sourceState.getName() + Strings.B_SUBSETEQ + Utils.getSuperState(Utils.getStatemachine(sourceState)).getName(); 
		
	}
}
