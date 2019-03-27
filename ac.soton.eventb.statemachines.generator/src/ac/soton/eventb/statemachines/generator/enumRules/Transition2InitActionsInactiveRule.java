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
package ac.soton.eventb.statemachines.generator.enumRules;

import java.util.ArrayList;
import java.util.List;

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
 * This rule translates a root state-machine using the enumeration (single variable) translation.
 * It generates the initialisation actions to initialise the state-machine and all its nested-state-machines
 * to a null state that represents that the state-machines are not currently active.
 * 
 */
public class Transition2InitActionsInactiveRule extends AbstractEventBGeneratorRule  implements IRule {

	private List<TranslationDescriptor> generatedElements;
	private Statemachine rootSM;
	private Event initEvent;
	
	@Override
	public boolean enabled(EObject sourceElement) throws Exception{
		initEvent = Utils.getTargetInitialisationEvent();
		rootSM = Utils.isRootStatemachine((Statemachine)sourceElement)? (Statemachine)sourceElement : null;
		return 	rootSM != null && initEvent != null &&
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
		this.generatedElements = generatedElements;
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

	
	private boolean canGenerate(Statemachine sm){
		return 	Find.generatedElement(generatedElements, initEvent, actions, Strings.INIT_ + sm.getName()) == null;
	}
}

