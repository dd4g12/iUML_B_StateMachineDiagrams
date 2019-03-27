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
import org.eventb.emf.core.context.Axiom;
import org.eventb.emf.core.context.Context;
import org.eventb.emf.core.machine.Machine;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.eventb.utils.Find;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.TranslationKind;
import ac.soton.eventb.statemachines.generator.strings.Strings;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class Statemachine2PartitionAxiomRule extends AbstractEventBGeneratorRule implements IRule{

	private Statemachine rootStatemachine = null;
	private Machine machine;
	
	/**
	 * Only enabled for enumeration translation
	 */
	@Override
	public boolean enabled(EObject sourceElement) throws Exception  {
		rootStatemachine = Utils.getRootStatemachine((Statemachine) sourceElement);
		return rootStatemachine.getTranslation().equals(TranslationKind.SINGLEVAR) &&
				((Statemachine) sourceElement).getRefines() == null;
	}

	/**
	 * Waits until context has not being generated
	 */
	@Override
	public boolean dependenciesOK(EObject sourceElement, final List<TranslationDescriptor> generatedElements) throws Exception  {
		machine = (Machine) Utils.getTranslationTarget();
		for(Context ctx : machine.getSees()) {
			if(ctx.getName().equals(Strings.CTX_NAME(machine, rootStatemachine))){
				return true;
			}
		}
		return Find.generatedElement(generatedElements, Find.project(machine), components, Strings.CTX_NAME(machine, rootStatemachine)) != null;
	}
	
	/**
	 * Generates Parition axioms
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		Statemachine sourceSM = (Statemachine) sourceElement;
		Context ctx = (Context)Find.generatedElement(generatedElements, Find.project(machine), components, Strings.CTX_NAME(machine, rootStatemachine));
		if(ctx == null){
			for(Context ictx : machine.getSees())
				if(ictx.getName().equals(Strings.CTX_NAME(machine, rootStatemachine))){
					ctx = ictx;
					break;
				}
		}
		String nullPartition = "";
		if(Utils.hasParentState(sourceSM) || Utils.hasFinalState(sourceSM)){
			nullPartition = Utils.asSet(sourceSM.getName() + Strings._NULL);
		}else {
			nullPartition = null;
		}
		List<String> states = Utils.getStateNamesAsSingletons(sourceSM);
		if(nullPartition != null) states.add(nullPartition);
		Axiom newSet = (Axiom) Make.axiom(Strings.DISTINCT_STATES_IN_ + sourceSM.getName() + Strings._STATES,
				Strings.B_PARTITION + Utils.parenthesize(sourceSM.getName() + Strings._STATES + Strings.B_COM +
						Utils.toString(states, Strings.B_COM)),
				"");
		ret.add(Make.descriptor(ctx, axioms, newSet, 10));
		return ret;
	}
	
}
