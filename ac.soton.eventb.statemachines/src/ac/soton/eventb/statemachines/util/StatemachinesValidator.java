/**
 * Copyright (c) 2010
 * University of Southampton.
 * All rights reserved. This program and the accompanying materials  are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies this 
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 *
 * $Id$
 */
package ac.soton.eventb.statemachines.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eventb.emf.core.machine.Invariant;

import ac.soton.eventb.statemachines.AbstractNode;
import ac.soton.eventb.statemachines.Final;
import ac.soton.eventb.statemachines.Initial;
import ac.soton.eventb.statemachines.State;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.StatemachineOwner;
import ac.soton.eventb.statemachines.StatemachinesPackage;
import ac.soton.eventb.statemachines.Transition;
import ac.soton.eventb.statemachines.TranslationKind;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see ac.soton.eventb.statemachines.StatemachinesPackage
 * @generated
 */
public class StatemachinesValidator extends EObjectValidator {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2010\rUniversity of Southampton.\rAll rights reserved. This program and the accompanying materials  are made\ravailable under the terms of the Eclipse Public License v1.0 which accompanies this \rdistribution, and is available at http://www.eclipse.org/legal/epl-v10.html\n";

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final StatemachinesValidator INSTANCE = new StatemachinesValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "ac.soton.eventb.statemachines";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatemachinesValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return StatemachinesPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case StatemachinesPackage.STATEMACHINE:
				return validateStatemachine((Statemachine)value, diagnostics, context);
			case StatemachinesPackage.STATEMACHINE_OWNER:
				return validateStatemachineOwner((StatemachineOwner)value, diagnostics, context);
			case StatemachinesPackage.TRANSITION:
				return validateTransition((Transition)value, diagnostics, context);
			case StatemachinesPackage.ABSTRACT_NODE:
				return validateAbstractNode((AbstractNode)value, diagnostics, context);
			case StatemachinesPackage.STATE:
				return validateState((State)value, diagnostics, context);
			case StatemachinesPackage.INITIAL:
				return validateInitial((Initial)value, diagnostics, context);
			case StatemachinesPackage.FINAL:
				return validateFinal((Final)value, diagnostics, context);
			case StatemachinesPackage.TRANSLATION_KIND:
				return validateTranslationKind((TranslationKind)value, diagnostics, context);
			default:
				return true;
		}
	}

//	/**
//	 * Validates the rootHasInitial constraint of '<em>Abstract Statemachine</em>'.
//	 * <!-- begin-user-doc -->
//	 * Root statemachine has an initial state.
//	 * <!-- end-user-doc -->
//	 * @generated NOT
//	 */
//	public boolean validateAbstractStatemachine_rootHasInitial(AbstractStatemachine abstractStatemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
//		if (abstractStatemachine.eContainer() instanceof Machine
//				&& EcoreUtil.getObjectByType(abstractStatemachine.getNodes(), StatemachinesPackage.eINSTANCE.getInitial()) == null) {
//			if (diagnostics != null) {
//				diagnostics.add
//					(createDiagnostic
//						(Diagnostic.WARNING,
//						 DIAGNOSTIC_SOURCE,
//						 0,
//						 "_UI_GenericConstraint_diagnostic",
//						 new Object[] { "Root statemachine should have an initial state", getObjectLabel(abstractStatemachine, context) },
//						 new Object[] { abstractStatemachine },
//						 context));
//			}
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * Validates the hasAtMostOneInitial constraint of '<em>Abstract Statemachine</em>'.
//	 * <!-- begin-user-doc -->
//	 * Abstract statemachine has at most one initial state.
//	 * <!-- end-user-doc -->
//	 * @generated NOT
//	 */
//	public boolean validateAbstractStatemachine_hasAtMostOneInitial(AbstractStatemachine abstractStatemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
//		if (EcoreUtil.getObjectsByType(abstractStatemachine.getNodes(), StatemachinesPackage.eINSTANCE.getInitial()).size() > 1) {
//			if (diagnostics != null) {
//				diagnostics.add
//					(createDiagnostic
//						(Diagnostic.ERROR,
//						 DIAGNOSTIC_SOURCE,
//						 0,
//						 "_UI_GenericConstraint_diagnostic",
//						 new Object[] { "Statemachine cannot have more than one initial state", getObjectLabel(abstractStatemachine, context) },
//						 new Object[] { abstractStatemachine },
//						 context));
//			}
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * Validates the hasAtMostOneFinal constraint of '<em>Abstract Statemachine</em>'.
//	 * <!-- begin-user-doc -->
//	 * Abstract statemachine has at most one final state.
//	 * <!-- end-user-doc -->
//	 * @generated NOT
//	 */
//	public boolean validateAbstractStatemachine_hasAtMostOneFinal(AbstractStatemachine abstractStatemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
//		if (EcoreUtil.getObjectsByType(abstractStatemachine.getNodes(), StatemachinesPackage.eINSTANCE.getFinal()).size() > 1) {
//			if (diagnostics != null) {
//				diagnostics.add
//					(createDiagnostic
//						(Diagnostic.ERROR,
//						 DIAGNOSTIC_SOURCE,
//						 0,
//						 "_UI_GenericConstraint_diagnostic",
//						 new Object[] { "Statemachine cannot have more than one final state", getObjectLabel(abstractStatemachine, context) },
//						 new Object[] { abstractStatemachine },
//						 context));
//			}
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * Validates the hasInitialIfIncomingExternal constraint of '<em>Abstract Statemachine</em>'.
//	 * <!-- begin-user-doc -->
//	 * Has an initial state if incoming external transitions exist.
//	 * <!-- end-user-doc -->
//	 * @generated NOT
//	 */
//	public boolean validateAbstractStatemachine_hasInitialIfIncomingExternal(AbstractStatemachine abstractStatemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
//		EObject container = abstractStatemachine.eContainer();
//		if (container != null 
//				&& container instanceof AbstractState
//				&& ((AbstractState) container).getIncoming().size() > 0 
//				&& EcoreUtil.getObjectByType(abstractStatemachine.getNodes(), StatemachinesPackage.eINSTANCE.getInitial()) == null) {
//			if (diagnostics != null) {
//				diagnostics.add
//					(createDiagnostic
//						(Diagnostic.ERROR,
//						 DIAGNOSTIC_SOURCE,
//						 0,
//						 "_UI_GenericConstraint_diagnostic",
//						 new Object[] { "Statemachine must have initial state if parent state has incoming transition(s)", getObjectLabel(abstractStatemachine, context) },
//						 new Object[] { abstractStatemachine },
//						 context));
//			}
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * Validates the hasInitialIfOutgoingLocal constraint of '<em>Abstract Statemachine</em>'.
//	 * <!-- begin-user-doc -->
//	 * Has an initial state if outgoing local transitions exist.
//	 * <!-- end-user-doc -->
//	 * @generated NOT
//	 */
//	public boolean validateAbstractStatemachine_hasInitialIfOutgoingLocal(AbstractStatemachine abstractStatemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
//		if (EcoreUtil.getObjectByType(abstractStatemachine.getNodes(), StatemachinesPackage.eINSTANCE.getInitial()) == null) {
//			Collection<ANY> anyStates = EcoreUtil.getObjectsByType(abstractStatemachine.getNodes(), StatemachinesPackage.eINSTANCE.getANY());
//			for (ANY anyState : anyStates) {
//				if (anyState.getIncoming().size() > 0) {
//					if (diagnostics != null) {
//						diagnostics.add
//							(createDiagnostic
//								(Diagnostic.ERROR,
//								 DIAGNOSTIC_SOURCE,
//								 0,
//								 "_UI_GenericConstraint_diagnostic",
//								 new Object[] { "Statemachine must have initial state if parent state has incoming local transition(s)", getObjectLabel(abstractStatemachine, context) },
//								 new Object[] { abstractStatemachine },
//								 context));
//					}
//					return false;
//				}
//			}
//		}
//		return true;
//	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractNode(AbstractNode abstractNode, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(abstractNode, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransition(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validateTransition_notToInitial(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validateTransition_notFromFinal(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validateTransition_notFromInitialToFinal(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validateTransition_elaborates(transition, diagnostics, context);
		return result;
	}

	/**
	 * Validates the notToInitial constraint of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * Not an incoming transition to initial state.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTransition_notToInitial(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (transition.getTarget() instanceof Initial) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "Transition cannot go to initial state", getObjectLabel(transition, context) },
						 new Object[] { transition },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the notFromFinal constraint of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * Not an outgoing transition from final state.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTransition_notFromFinal(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (transition.getSource() instanceof Final) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "Transition cannot go from final state", getObjectLabel(transition, context) },
						 new Object[] { transition },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the notFromInitialToFinal constraint of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * Not from initial to final state.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTransition_notFromInitialToFinal(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (transition.getSource() instanceof Initial && transition.getTarget() instanceof Final) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "Transition cannot go directly from initial to final state", getObjectLabel(transition, context) },
						 new Object[] { transition },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the elaborates constraint of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * Transition elaborates events.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateTransition_elaborates(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// if transitions does not elaborate an event AND
		// it is either on root OR
		// in nested statemachine, in which it is NOT initial or final transition
		if (transition.getElaborates().isEmpty()
				&& (transition.getTarget().eContainer().eContainer() instanceof State == false
						|| (transition.getSource() instanceof Initial == false
								&& transition.getTarget() instanceof Final == false))) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.WARNING,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "Transition should elaborate an event", getObjectLabel(transition, context) },
						 new Object[] { transition },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStatemachine(Statemachine statemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validateStatemachine_hasValidName(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validateStatemachine_concreteHasNoRefinedStates(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validateStatemachine_refinedHasNoConcreteStates(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validateStatemachine_hasOneInitial(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validateStatemachine_hasOneFinal(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validateStatemachine_rootHasInitial(statemachine, diagnostics, context);
		if (result || diagnostics != null) result &= validateStatemachine_hasInitialIfIncoming(statemachine, diagnostics, context);
		return result;
	}

	/**
	 * Validates the hasValidName constraint of '<em>Statemachine</em>'.
	 * <!-- begin-user-doc -->
	 * Statemachine has a valid name.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStatemachine_hasValidName(Statemachine statemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String name = statemachine.getName();
		if (name == null || !name.trim().matches("\\w+")) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.WARNING,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "Statemachine should have a valid name", getObjectLabel(statemachine, context) },
						 new Object[] { statemachine },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the concreteHasNoRefinedStates constraint of '<em>Statemachine</em>'.
	 * <!-- begin-user-doc -->
	 * Concrete statemachine has no refined states.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStatemachine_concreteHasNoRefinedStates(Statemachine statemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (statemachine.getRefines() == null)
			for (AbstractNode node : statemachine.getNodes())
				if (node instanceof State && ((State) node).getRefines() != null) {
					if (diagnostics != null) {
						diagnostics.add(createDiagnostic(
										Diagnostic.ERROR,
										DIAGNOSTIC_SOURCE,
										0,
										"_UI_GenericConstraint_diagnostic",
										new Object[] {
												"Concrete statemachine cannot contain refined states",
												getObjectLabel(statemachine,
														context) },
										new Object[] { statemachine }, context));
					}
					return false;
				}
		return true;
	}

	/**
	 * Validates the refinedHasNoConcreteStates constraint of '<em>Statemachine</em>'.
	 * <!-- begin-user-doc -->
	 * Refined statemachine has no concrete states. 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStatemachine_refinedHasNoConcreteStates(Statemachine statemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (statemachine.getRefines() != null) 
			for (AbstractNode node : statemachine.getNodes())
				if (node instanceof State && ((State) node).getRefines() == null) {
					if (diagnostics != null) {
						diagnostics.add(createDiagnostic(
										Diagnostic.ERROR,
										DIAGNOSTIC_SOURCE,
										0,
										"_UI_GenericConstraint_diagnostic",
										new Object[] {
												"Refined statemachine cannot contain concrete states",
												getObjectLabel(statemachine,
														context) },
										new Object[] { statemachine }, context));
					}
					return false;
				}
		return true;
	}

	/**
	 * Validates the hasOneInitial constraint of '<em>Statemachine</em>'.
	 * <!-- begin-user-doc -->
	 * Statemachine has only one initial state.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStatemachine_hasOneInitial(Statemachine statemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (EcoreUtil.getObjectsByType(statemachine.getNodes(), StatemachinesPackage.eINSTANCE.getInitial()).size() > 1) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "Statemachine can have only one initial state", getObjectLabel(statemachine, context) },
						 new Object[] { statemachine },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasOneFinal constraint of '<em>Statemachine</em>'.
	 * <!-- begin-user-doc -->
	 * Statemachine has only one final state.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStatemachine_hasOneFinal(Statemachine statemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (EcoreUtil.getObjectsByType(statemachine.getNodes(), StatemachinesPackage.eINSTANCE.getFinal()).size() > 1) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "Statemachine can have only one final state", getObjectLabel(statemachine, context) },
						 new Object[] { statemachine },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the rootHasInitial constraint of '<em>Statemachine</em>'.
	 * <!-- begin-user-doc -->
	 * Root statemachine has initial state.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStatemachine_rootHasInitial(Statemachine statemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (statemachine.eContainer() instanceof State == false
				&& EcoreUtil.getObjectByType(statemachine.getNodes(), StatemachinesPackage.eINSTANCE.getInitial()) == null) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "Root statemachine should have initial state", getObjectLabel(statemachine, context) },
						 new Object[] { statemachine },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasInitialIfIncoming constraint of '<em>Statemachine</em>'.
	 * <!-- begin-user-doc -->
	 * Statemachine has initial state if container state has incoming transitions.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateStatemachine_hasInitialIfIncoming(Statemachine statemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
		EObject container = statemachine.eContainer();
		if (container instanceof State
				&& ((State) container).getIncoming().size() > 0 
				&& EcoreUtil.getObjectByType(statemachine.getNodes(), StatemachinesPackage.eINSTANCE.getInitial()) == null) {
			EList<EObject> nestedStates = statemachine.getAllContained(
					StatemachinesPackage.eINSTANCE.getState(), true);
			for (Transition transition : ((State) container).getOutgoing())
				if (nestedStates.contains(transition.getTarget()))
					return true;
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] {
										"Statemachine must have initial state if container state has incoming transition(s)",
										getObjectLabel(statemachine, context) },
								new Object[] { statemachine }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the hasNoRefinedStates constraint of '<em>Statemachine</em>'.
	 * <!-- begin-user-doc -->
	 * Statemachine has no refined states.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unused")
	public boolean validateStatemachine_hasNoRefinedStates(Statemachine statemachine, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (false){//EcoreUtil.getObjectByType(statemachine.eContents(), StatemachinesPackage.eINSTANCE.getRefinedState()) != null) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "Concrete statemachine cannot contain refined states", getObjectLabel(statemachine, context) },
						 new Object[] { statemachine },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateState(State state, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(state, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_hasValidName(state, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_concreteHasNoRefinedStatemachines(state, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_validInvariants(state, diagnostics, context);
		return result;
	}

	/**
	 * Validates the hasValidName constraint of '<em>State</em>'.
	 * <!-- begin-user-doc -->
	 * State has a valid name.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateState_hasValidName(State state, DiagnosticChain diagnostics, Map<Object, Object> context) {
		String name = state.getName();
		if (name == null || !name.trim().matches("\\w+")) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "State must have a valid name", getObjectLabel(state, context) },
						 new Object[] { state },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the concreteHasNoRefinedStatemachines constraint of '<em>State</em>'.
	 * <!-- begin-user-doc -->
	 * Concrete state has no refined statemachines.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateState_concreteHasNoRefinedStatemachines(State state, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (state.getRefines() == null)
			for (Statemachine statemachine : state.getStatemachines())
				if (statemachine.getRefines() != null) {
					if (diagnostics != null) {
						diagnostics
								.add(createDiagnostic(
										Diagnostic.ERROR,
										DIAGNOSTIC_SOURCE,
										0,
										"_UI_GenericConstraint_diagnostic",
										new Object[] {
												"Concrete state cannot contain refined statemachines",
												getObjectLabel(state, context) },
										new Object[] { state }, context));
					}
					return false;
				}
		return true;
	}

	/**
	 * Validates the validInvariants constraint of '<em>State</em>'.
	 * <!-- begin-user-doc -->
	 * State has valid invariants.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateState_validInvariants(State state, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = true;
		for (Invariant invariant : state.getInvariants()) {
			String name = invariant.getName();
			if (name == null || name.trim().isEmpty()) {
				if (diagnostics != null) {
					diagnostics.add
						(createDiagnostic
							(Diagnostic.ERROR,
							 DIAGNOSTIC_SOURCE,
							 0,
							 "_UI_GenericConstraint_diagnostic",
							 new Object[] { "Invariant must have a name", getObjectLabel(invariant, context) },
							 new Object[] { invariant },
							 context));
				}
				result = false;
			}
		}
		
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInitial(Initial initial, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(initial, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(initial, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(initial, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(initial, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(initial, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(initial, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(initial, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(initial, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(initial, diagnostics, context);
		if (result || diagnostics != null) result &= validateInitial_hasOutgoing(initial, diagnostics, context);
		return result;
	}

	/**
	 * Validates the hasOutgoing constraint of '<em>Initial</em>'.
	 * <!-- begin-user-doc -->
	 * Initial state has outgoing transitions.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateInitial_hasOutgoing(Initial initial, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (initial.getOutgoing().isEmpty()) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.WARNING,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "Initial state should have an outgoing transition", getObjectLabel(initial, context) },
						 new Object[] { initial },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFinal(Final final_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_NoCircularContainment(final_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMultiplicityConforms(final_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(final_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(final_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(final_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(final_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(final_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(final_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(final_, diagnostics, context);
		if (result || diagnostics != null) result &= validateFinal_hasIncoming(final_, diagnostics, context);
		return result;
	}

	/**
	 * Validates the hasIncoming constraint of '<em>Final</em>'.
	 * <!-- begin-user-doc -->
	 * Final state has incoming transitions.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateFinal_hasIncoming(Final final_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (final_.getIncoming().isEmpty()) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.WARNING,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "Final state should have an incoming transition", getObjectLabel(final_, context) },
						 new Object[] { final_ },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStatemachineOwner(StatemachineOwner statemachineOwner, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(statemachineOwner, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTranslationKind(TranslationKind translationKind, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //StatemachinesValidator