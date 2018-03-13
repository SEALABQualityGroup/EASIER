/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices.provider;


import java.util.Collection;
import java.util.List;

import mapmeasurestoindices.MapmeasurestoindicesFactory;
import mapmeasurestoindices.MapmeasurestoindicesPackage;
import mapmeasurestoindices.MeasureMapping;

import metamodel.mmaemilia.Expressions.ExpressionsFactory;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link mapmeasurestoindices.MeasureMapping} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MeasureMappingItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasureMappingItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addMeasureNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Measure Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMeasureNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MeasureMapping_measureName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_MeasureMapping_measureName_feature", "_UI_MeasureMapping_type"),
				 MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__MEASURE_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__SELECTOR);
			childrenFeatures.add(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__INSTANCES);
			childrenFeatures.add(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__ACTIONS);
			childrenFeatures.add(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__ARCHI_INTERACTIONS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns MeasureMapping.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/MeasureMapping"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((MeasureMapping)object).getMeasureName();
		return label == null || label.length() == 0 ?
			getString("_UI_MeasureMapping_type") :
			getString("_UI_MeasureMapping_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(MeasureMapping.class)) {
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__MEASURE_NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__SELECTOR:
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__INSTANCES:
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__ACTIONS:
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__ARCHI_INTERACTIONS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__SELECTOR,
				 ExpressionsFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__SELECTOR,
				 ExpressionsFactory.eINSTANCE.createListExpr()));

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__SELECTOR,
				 ExpressionsFactory.eINSTANCE.createArrayExpr()));

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__SELECTOR,
				 ExpressionsFactory.eINSTANCE.createRecordExpr()));

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__SELECTOR,
				 ExpressionsFactory.eINSTANCE.createIdentExpr()));

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__SELECTOR,
				 ExpressionsFactory.eINSTANCE.createMathFunctions()));

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__SELECTOR,
				 ExpressionsFactory.eINSTANCE.createRelationalExpr()));

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__SELECTOR,
				 ExpressionsFactory.eINSTANCE.createArithExpr()));

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__SELECTOR,
				 ExpressionsFactory.eINSTANCE.createPseudo_random_num_gen()));

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__SELECTOR,
				 ExpressionsFactory.eINSTANCE.createBooleanExpr()));

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__INSTANCES,
				 MapmeasurestoindicesFactory.eINSTANCE.createAeiMeasure()));

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__ACTIONS,
				 MapmeasurestoindicesFactory.eINSTANCE.createActionMeasure()));

		newChildDescriptors.add
			(createChildParameter
				(MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING__ARCHI_INTERACTIONS,
				 MapmeasurestoindicesFactory.eINSTANCE.createArchiIntMeasure()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return MapMeasuresToIndicesEditPlugin.INSTANCE;
	}

}
