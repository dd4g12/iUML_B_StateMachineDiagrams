package ac.soton.eventb.statemachines.generator.enumRules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
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

public class RootStatemachine2NewContextRule extends AbstractEventBGeneratorRule implements IRule{
	
	@Override
	public boolean enabled(EObject sourceElement) throws Exception  {	
		return Utils.isRootStatemachine((Statemachine)sourceElement) &&
				((Statemachine) sourceElement).getTranslation().equals(TranslationKind.SINGLEVAR); 
	}
	
	/**
	 * Generates the implicit context
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		
		Machine container = (Machine)EcoreUtil.getRootContainer(sourceElement);

		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
	
		Context implicitContext =  (Context) Make.context(Strings.CTX_NAME((Statemachine)sourceElement), "");
		
		ret.add(Make.descriptor(Find.project(container), components, implicitContext ,1));
		ret.add(Make.descriptor(container, seesNames, implicitContext.getName(), 1));

		Statemachine refinedStatemachine = ((Statemachine)sourceElement).getRefines();
		if  (refinedStatemachine != null){
			String refinedStatemachineContextName = Strings.CTX_NAME(refinedStatemachine);
			if(!implicitContext.getExtendsNames().contains(refinedStatemachineContextName)){
				implicitContext.getExtendsNames().add(refinedStatemachineContextName);
			}
		}
		return ret;
	}
	
}



	
	
	
