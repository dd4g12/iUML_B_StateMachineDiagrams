/*******************************************************************************
 *  Copyright (c) 2010-2019 University of Southampton.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *  Contributors:
 *  University of Southampton - Initial implementation
 *******************************************************************************/
package ac.soton.eventb.statemachines.generator.varRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eventb.emf.core.machine.Action;
import org.eventb.emf.core.machine.Event;

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

/**
 * This rule translates a root state-machine using the variables (multi-variable) translation.
 * It generates the initialisation actions to initialise the state-machine states and all its nested-state-machines
 * to a null value that represents that the state-machines are not currently active.
 * 
 */
public class Initial2InitActionsInactiveRule extends AbstractEventBGeneratorRule  implements IRule {

	private Map<State, Boolean> generatedStatus;
	private List<TranslationDescriptor> generatedElements;
	private Statemachine rootSm;
	private Event initEvent;
	
	@Override
	public boolean enabled(EObject sourceElement) throws Exception{
		initEvent = Utils.getTargetInitialisationEvent();
		rootSm = Utils.isRootStatemachine((Statemachine)sourceElement)? (Statemachine)sourceElement : null;
		return 	rootSm != null && initEvent != null &&
				((Statemachine)sourceElement).getTranslation().equals(TranslationKind.MULTIVAR);
	}


	@Override
	public boolean fireLate() {
		return true;
	}
	
	/**
	 * Initial2InitActions
	 * 
	 * Generates the intialisation actions
	 * Implementing as previous implementation
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		this.generatedElements = generatedElements;
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		
		//Map that stores if the init action was generated or not
		generatedStatus = new HashMap<State, Boolean>();
		
		List<Action> generatedActions = (generateInactive(initEvent));

		for(Action a : generatedActions){
			ret.add(Make.descriptor(initEvent, actions, a, 10));
		}
		return ret;
	}

	/**
	 * Generate initialisations for all states to be initialised as inactive
	 * @return
	 */
	private List<Action> generateInactive(Event event){
		List<Action> ret = new ArrayList<Action>();
		ret.addAll(statemachine2initActionsInactive(rootSm, event));		
		return ret;
	}

	/**
	 * Transforms statemachine to initialisation actions on inactive states.
	 * @param sm
	 * @param event
	 * @return
	 */
	private List<Action> statemachine2initActionsInactive(Statemachine sm, Event event){
		List<Action> ret = new ArrayList<Action>();
		for(AbstractNode abs : sm.getNodes()){
			if(abs instanceof State){
				ret.addAll(state2initActionsInactive( (State)abs , event ));
			}
		}
		return ret;
	}

	private List<Action> state2initActionsInactive(State s, Event event){
		List<Action> ret = new ArrayList<Action>();
		String value;
		if(rootSm.getInstances() == null)
			value = Strings.B_FALSE;
		else
			value = Strings.B_EMPTYSET;
		if(generatedStatus.get(s) == null && Find.generatedElement(generatedElements, initEvent, actions, Strings.INIT_ + s.getName()) == null)
			ret.add(state2initAction(s, value));
		for(Statemachine sm : s.getStatemachines()){
			ret.addAll(statemachine2initActionsInactive(sm, event));
		}
		return ret;
	}
	
	private Action state2initAction(State s, String value){
		//Do nothing if initialisation to the given state has already been done
		//if(generatedStatus.get(s) != null) return null;
		generatedStatus.put(s, new Boolean(true));
		return (Action) Make.action(Strings.INIT_ + s.getName(),
				s.getName() + Strings.B_BEQ + value);
	}


}


