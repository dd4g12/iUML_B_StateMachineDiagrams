package ac.soton.eventb.statemachines.generator.enumRules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eventb.emf.core.context.CarrierSet;
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

public class Statemachine2SetRule extends AbstractEventBGeneratorRule implements IRule{

	private Statemachine rootStatemachine = null;
	private Machine machine;
	
	/**
	 * Only enabled for enumeration translation
	 */
	@Override
	public boolean enabled(EObject sourceElement) throws Exception  {
		rootStatemachine = Utils.getRootStatemachine((Statemachine) sourceElement);
		return ((Statemachine)sourceElement).getRefines() == null &&
				rootStatemachine.getTranslation().equals(TranslationKind.SINGLEVAR);
	}

	/**
	 * Waits until context has been generated
	 */
	@Override
	public boolean dependenciesOK(EObject sourceElement, final List<TranslationDescriptor> generatedElements) throws Exception  {
		machine = (Machine) Utils.getTranslationTarget();
		for(Context ctx : machine.getSees()) {
			if(ctx.getName().equals(Strings.CTX_NAME(machine, rootStatemachine))) return true;
		}
		return Find.generatedElement(generatedElements, Find.project(machine), components, Strings.CTX_NAME(machine, rootStatemachine)) != null;
	}
	
	/**
	 * Generates the the sets for the enumeration translation
	 */
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		Statemachine sourceSM = (Statemachine) sourceElement;
		Context ctx = (Context)Find.generatedElement(generatedElements, Find.project(machine), components, Strings.CTX_NAME(machine, rootStatemachine));
		if(ctx == null){
			for(Context ictx : machine.getSees()) {
				if(ictx.getName().equals(Strings.CTX_NAME(machine, rootStatemachine))){
					ctx = ictx;
					break;
				}
			}
		}
		CarrierSet newSet = (CarrierSet) Make.set(sourceSM.getName() + Strings._STATES, "");
		ret.add(Make.descriptor(ctx, sets, newSet, 1));
		return ret;
	}
	
}
