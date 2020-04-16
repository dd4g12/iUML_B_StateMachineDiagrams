/*
 * Copyright (c) 2014-2019 University of Southampton.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package ac.soton.eventb.statemachines.diagram.sheet.custom;

import java.util.List;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import ac.soton.eventb.emf.diagrams.sheet.AbstractEnumerationPropertySection;
import ac.soton.eventb.statemachines.State;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.StatemachinesPackage;

/**
 * Refines property section for State.
 * 
 *
 */
public class RefinesStatePropertySection extends AbstractEnumerationPropertySection {

	@Override
	protected EStructuralFeature getFeature() {
		return StatemachinesPackage.Literals.STATE__REFINES;
	}

	@Override
	protected String[] getEnumerationFeatureValues() {
		EList<EObject> statesToRefine = getStatesToRefine();
		String[] values = new String[statesToRefine.size()];
		int i = 0;
		for (EObject sm : statesToRefine) {
			values[i++] = sm == null ? ""
					: ((State) sm).getName();
		}
		return values;
	}

	@Override
	protected String getFeatureAsText() {
		State state = ((State) eObject).getRefines();
		return state == null ? "" 
				: ((State) state).getName();
	}

	@Override
	protected String getLabelText() {
		return "Refines:";
	}

	@Override
	protected List<EObject> getAvailableDataElements(){
		return (List<EObject>)getStatesToRefine();
	}
	
	@SuppressWarnings("unchecked")
	private EList<EObject> getStatesToRefine(){
		Statemachine rootStatemachine = getRootSM();
		Statemachine refinedStatemachine = rootStatemachine.getRefines();
		if (refinedStatemachine!= null) {
			return refinedStatemachine.getAllContained(StatemachinesPackage.Literals.STATE, true);
		}
		return (EList<EObject>) ECollections.EMPTY_ELIST;
	}

	private Statemachine getRootSM() {
		Statemachine rootStatemachine = (Statemachine)owner.eContainer();
		while (rootStatemachine.eContainer() instanceof State
				&& rootStatemachine.eContainer().eContainer() instanceof Statemachine)
			rootStatemachine = (Statemachine) rootStatemachine.eContainer().eContainer();
		return rootStatemachine;
	}
	
}
