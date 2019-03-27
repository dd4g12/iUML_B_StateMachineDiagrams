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
package ac.soton.eventb.statemachines.generator.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Parameter;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.Transition;
import ac.soton.eventb.statemachines.generator.utils.Utils;


/**
 * Works for both kinds of translations
 * @author matheus
 *
 */

public class Transition2ParameterRule extends AbstractEventBGeneratorRule  implements IRule {
	
	/**
	 * Trasition2Parameters
	 * 
	 * Generates the parameter for events elaborated by the transition
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
	
		Transition sourceTransition = (Transition) sourceElement;
		
		for(Event ev : sourceTransition.getElaborates()){
			if(!ev.getName().equals(Utils.INITIALISATION_EVENT_NAME)){
				List<Parameter> generatedParameters = generateParameters(sourceTransition, ev);
				for(Parameter p : generatedParameters){
					ret.add(Make.descriptor(ev, parameters, Make.parameter(p.getName(), p.getComment()), 10));
				}
				
				
			}
		}
		
		
		return ret;
		
	}
	
	/**
	 * Method to generate parameters from a given function
	 * @param t
	 * @return
	 */
	private List<Parameter> generateParameters(Transition t, Event event){
		List<Parameter> ret = new ArrayList<Parameter>();
		Statemachine rootSm = Utils.getRootStatemachine(t.getTarget());
		
		if(rootSm.getInstances() != null){
			ret.add(rootStatemachine2parameter(rootSm));
		}
		
		for(Parameter p : t.getParameters()){
			ret.add(transitionParameter2parameter(p));
		}
		
		return ret;
	}
	
	
	
	private Parameter transitionParameter2parameter(Parameter p){
		return (Parameter) Make.parameter(p.getName(),p.getComment());
	}
	
	
	/**
	 * Transforms a root Statemachine into parameter
	 * NOTE: Do not pass a non root state machine to it. No check is done for efficiency 
	 * @param sm
	 * @return
	 */
	private Parameter rootStatemachine2parameter(Statemachine sm){
		return (Parameter) Make.parameter(sm.getSelfName());
	}
	

}
