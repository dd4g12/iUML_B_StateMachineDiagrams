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

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.State;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.TranslationKind;
import ac.soton.eventb.statemachines.generator.strings.Strings;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class Statemachine2SuperstateInvariantRule extends AbstractEventBGeneratorRule  implements IRule {

	/**
	 * Only enabled for enumeration translation. Root statemachines is not used
	 * 
	 */
	@Override
	public boolean enabled(EObject sourceElement) throws Exception  {
		TranslationKind translatioKind = Utils.getRootStatemachine((Statemachine) sourceElement).getTranslation();
		return !Utils.isRootStatemachine((Statemachine) sourceElement) &&
				((Statemachine) sourceElement).getRefines() == null && 
				translatioKind.equals(TranslationKind.SINGLEVAR);
	}
	
	/**
	 * Generates superstate axiom in the implicit context
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		Statemachine sourceSM = (Statemachine) sourceElement;
		Machine machine = (Machine) Utils.getTranslationTarget();
		String name = Strings.SUPERSTATEOF_ +  sourceSM.getName();
		String predicate = generatePredicate(sourceSM);
		ret.add(Make.descriptor(machine, invariants, Make.invariant(name, predicate, ""), 6));
		return ret;
	}

	private String generatePredicate(Statemachine sourceSM) {
		State parentState = Utils.getSuperState(sourceSM);
		if(Utils.getRootStatemachine(sourceSM).getInstances() == null)
			return sourceSM.getName() + Strings.B_NEQ + sourceSM.getName()+
					Strings._NULL + Strings.B_EQUIV + Utils.getStatemachine(parentState).getName() +
					Strings.B_EQ + parentState.getName();
		else
			return Strings.B_DOM + Utils.parenthesize(sourceSM.getName() + Strings.B_RANSUB + Utils.asSet(sourceSM.getName() + Strings._NULL)) +
					Strings.B_EQ + Strings.B_DOM + 
					Utils.parenthesize(Utils.getStatemachine(parentState).getName() +Strings.B_RANRES + Utils.asSet(parentState.getName()));
	}
	
}

