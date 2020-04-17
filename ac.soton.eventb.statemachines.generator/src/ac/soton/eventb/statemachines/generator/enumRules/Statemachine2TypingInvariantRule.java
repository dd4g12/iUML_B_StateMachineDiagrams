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
import org.eventb.emf.core.EventBNamedCommentedElement;
import org.eventb.emf.core.machine.Invariant;
import org.eventb.emf.core.machine.Machine;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.TranslationKind;
import ac.soton.eventb.statemachines.generator.strings.Strings;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class Statemachine2TypingInvariantRule extends AbstractEventBGeneratorRule  implements IRule {

	@Override
	public boolean enabled(EObject sourceElement) throws Exception  {
		TranslationKind translatioKind = Utils.getRootStatemachine((Statemachine) sourceElement).getTranslation();
		Statemachine sourceSM = (Statemachine) sourceElement;
		return translatioKind.equals(TranslationKind.SINGLEVAR) &&
				sourceSM.getRefines() == null;
	}
	
	/**
	 * States2Variables
	 * 
	 * Generates a new variable named as the states it represents
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		Statemachine sourceSM = (Statemachine) sourceElement;
		Machine machine = (Machine) Utils.getTranslationTarget();
		Statemachine rootSM = Utils.getRootStatemachine(sourceSM);
		ret.add(Make.descriptor(machine, invariants, generateInvariant(rootSM.getInstances(), sourceSM), 1));
		return ret;
		
	}
	
	private Invariant generateInvariant(EventBNamedCommentedElement instances, Statemachine sourceSM){
		String name = Strings.TYPEOF_ + sourceSM.getName();
		String predicate = "";
		if(instances == null)
			predicate = sourceSM.getName() + Strings.B_IN + sourceSM.getName() + Strings._STATES;
		else
			predicate = sourceSM.getName() + Strings.B_IN + instances.getName() + Strings.B_TFUN + sourceSM.getName() + Strings._STATES;
		return Make.invariant(name, predicate,"");
	}

}

