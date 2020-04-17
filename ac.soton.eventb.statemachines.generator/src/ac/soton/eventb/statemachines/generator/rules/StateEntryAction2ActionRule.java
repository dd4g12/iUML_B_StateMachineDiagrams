/*******************************************************************************
 *  Copyright (c) 2010-2017 University of Southampton.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *  Contributors:
 *  University of Southampton - Initial implementation
 *******************************************************************************/
package ac.soton.eventb.statemachines.generator.rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eventb.emf.core.machine.Action;
import org.eventb.emf.core.machine.Event;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.State;
import ac.soton.eventb.statemachines.Transition;
import ac.soton.eventb.statemachines.generator.utils.Utils;

/**
 * Generation rule for State Entry Actions
 * 
 * @author cfs
 *
 */
public class StateEntryAction2ActionRule extends AbstractEventBGeneratorRule  implements IRule {
	
	/**
	 * StateEntryAction2Action
	 * 
	 * Generates actions on all events elaborated by incoming transitions from state entry actions.
	 * (If an event is elaborated by more than one incomer, the action is only added once).
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		Set<Event> events = new HashSet<Event>();
		for (Transition incomingTransition : ((State) sourceElement).getIncoming()){
			events.addAll(Utils.getAllUpstreamElaboratedEvents(incomingTransition));
		}
		for (Event event: events){
			for(Action a : ((State) sourceElement).getEntryActions()){
				ret.add(Make.descriptor(event, actions, Make.action(a.getName(), a.getAction(), "(Entry Action from "+((State) sourceElement).getName()+") " +a.getComment()), 10));
			}				
		}
		return ret;		
	} 
	
}