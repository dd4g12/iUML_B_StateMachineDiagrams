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
package ac.soton.eventb.statemachines.generator.varRules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eventb.emf.core.EventBNamedCommentedComponentElement;
import org.eventb.emf.core.context.Context;
import org.eventb.emf.core.machine.Machine;

import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.TranslationKind;
import ac.soton.eventb.statemachines.generator.strings.Strings;
import ac.soton.eventb.statemachines.generator.utils.Utils;

public class RemoveImplicitContextRule extends AbstractEventBGeneratorRule  implements IRule {

	@Override
	public boolean enabled(EObject sourceElement) throws Exception{
		Statemachine sm = (Statemachine) sourceElement;
		
		return Utils.isRootStatemachine(sm) &&
			    sm.getTranslation().equals(TranslationKind.MULTIVAR);//Variables translation only

	}
	
	

	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		EventBNamedCommentedComponentElement container = Utils.getTranslationTarget();
		
		if(((Machine)container).getSees().size() != 0){
			for(Context ctx : ((Machine)container).getSees()){
				if(ctx.getName().equals(Strings.CTX_NAME(container, (Statemachine)sourceElement))){
					//ret.add(Make.descriptor(Find.project(container), components, Make.context(container.getName() + Strings._IMPLICIT_CONTEXT,""), 1, true) );
					//ret.add(Make.descriptor(container, sees,  ctx , 1, true));
				}
			}
		}

		return ret;
		
	}

	
}