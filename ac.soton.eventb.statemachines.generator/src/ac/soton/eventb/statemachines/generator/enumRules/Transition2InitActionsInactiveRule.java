package ac.soton.eventb.statemachines.generator.enumRules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eventb.emf.core.machine.Action;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Machine;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Find;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.AbstractNode;
import ac.soton.eventb.statemachines.State;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.TranslationKind;
import ac.soton.eventb.statemachines.generator.strings.Strings;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class Transition2InitActionsInactiveRule extends AbstractEventBGeneratorRule  implements IRule {

	
	private Statemachine rootSM;
	private Event initEvent;
	private List<TranslationDescriptor> generatedElements;
	
	@Override
	public boolean enabled(EObject sourceElement) throws Exception{

		Machine container = (Machine)EcoreUtil.getRootContainer(sourceElement);
		return Utils.isRootStatemachine((Statemachine)sourceElement) &&
				getInitEvent(container) != null &&
				((Statemachine)sourceElement).getTranslation().equals(TranslationKind.SINGLEVAR);
		
	}
	
	@Override
	public boolean fireLate() {
		return true;
	}

	/**
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		List<Action> generatedActions = new ArrayList<Action>();
		rootSM = (Statemachine) sourceElement;
		
		this.generatedElements = generatedElements;
		Machine container = (Machine)EcoreUtil.getRootContainer(sourceElement);
		initEvent = getInitEvent(container); 
				
		
		generatedActions.addAll(statemachine2initActionsInactive(rootSM));
		
		
		for(Action a : generatedActions){
			ret.add(Make.descriptor(initEvent, actions, a, 1));
		}
		
		return ret;
	}
	
	private List<Action> statemachine2initActionsInactive(Statemachine sm) {
		List<Action> ret = new ArrayList<Action>();
		if(canGenerate(sm)){
			ret.add(statemachine2initActionInactive(sm));
		}
		for(AbstractNode node : sm.getNodes()){
			if(node instanceof State)
				for(Statemachine ism : ((State)node).getStatemachines())
					ret.addAll(statemachine2initActionsInactive(ism));
		}
		
		
		
		return ret;
	}

	private Action statemachine2initActionInactive(Statemachine sm) {
		String name = Strings.INIT_ + sm.getName();
		String expression = "";
				
		if(rootSM.getInstances() == null)
			expression = sm.getName() + Strings.B_BEQ + sm.getName() + Strings._NULL;
		else
			expression = sm.getName() + Strings.B_BEQ + rootSM.getInstances().getName() +
						 Strings.B_CPROD + Utils.asSet(sm.getName() + Strings._NULL);
		
		return (Action) Make.action(name, expression);
	}

	/**
	 * Finds the init event
	 * @param t
	 * @return
	 */
	private Event getInitEvent(Machine m){
		for(Event e : m.getEvents()){
			if(e.getName().equals(Strings.INIT))
				return e;
		}
		return null;
	}
	
	private boolean canGenerate(Statemachine sm){
		return 	Find.generatedElement(generatedElements, initEvent, actions, Strings.INIT_ + sm.getName()) == null;
	
	}
}

