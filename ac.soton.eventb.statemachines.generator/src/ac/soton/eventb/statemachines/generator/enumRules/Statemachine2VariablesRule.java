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
import org.eventb.emf.core.machine.Machine;
import org.eventb.emf.core.machine.Variable;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.TranslationKind;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class Statemachine2VariablesRule extends AbstractEventBGeneratorRule  implements IRule {

	@Override
	public boolean enabled(EObject sourceElement) throws Exception  {
		TranslationKind translatioKind = Utils.getRootStatemachine((Statemachine) sourceElement).getTranslation();
		return translatioKind.equals(TranslationKind.SINGLEVAR);
	}
	
	/**
	 * States2Variables
	 * 
	 * Generates a new variable named as the states it represents
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		Machine machine = (Machine) Utils.getTranslationTarget();
		Variable newVariable = Make.variable(( (Statemachine) sourceElement).getName(), "");
		ret.add(Make.descriptor(machine, variables, newVariable, 10));
		return ret;	
	}

}

