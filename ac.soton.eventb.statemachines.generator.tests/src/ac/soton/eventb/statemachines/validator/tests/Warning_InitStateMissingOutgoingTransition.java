/*******************************************************************************
 * Copyright (c) 2015 University of Southampton.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     University of Southampton - initial API and implementation
 *******************************************************************************/

package ac.soton.eventb.statemachines.validator.tests;

import ac.soton.eventb.statemachines.Initial;

/**
 * <p>
 *
 * </p>
 *
 * @author htson
 * @version
 * @see
 * @since
 */
public class Warning_InitStateMissingOutgoingTransition extends
		AbstractWarningDiagnostic implements IDiagnosticCause {

	/**
	 * @param severity
	 * @param signature
	 * @param objects
	 */
	public Warning_InitStateMissingOutgoingTransition(Initial initial) {
		super("Initial state should have an outgoing transition", initial);
	}

}
