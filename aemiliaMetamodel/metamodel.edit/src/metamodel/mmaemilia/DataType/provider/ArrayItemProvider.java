/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType.provider;


import java.util.Collection;
import java.util.List;

import metamodel.mmaemilia.DataType.Array;
import metamodel.mmaemilia.DataType.DataTypeFactory;
import metamodel.mmaemilia.DataType.DataTypePackage;

import metamodel.mmaemilia.Expressions.ExpressionsFactory;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link metamodel.mmaemilia.DataType.Array} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ArrayItemProvider
	extends NormalItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(DataTypePackage.Literals.ARRAY__ARRAY_ELEM_TYPE);
			childrenFeatures.add(DataTypePackage.Literals.ARRAY__LENGTH);
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
	 * This returns Array.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Array"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_Array_type");
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

		switch (notification.getFeatureID(Array.class)) {
			case DataTypePackage.ARRAY__ARRAY_ELEM_TYPE:
			case DataTypePackage.ARRAY__LENGTH:
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
				(DataTypePackage.Literals.ARRAY__ARRAY_ELEM_TYPE,
				 DataTypeFactory.eINSTANCE.createNormal()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__ARRAY_ELEM_TYPE,
				 DataTypeFactory.eINSTANCE.createInteger()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__ARRAY_ELEM_TYPE,
				 DataTypeFactory.eINSTANCE.createRecord()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__ARRAY_ELEM_TYPE,
				 DataTypeFactory.eINSTANCE.createArray()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__ARRAY_ELEM_TYPE,
				 DataTypeFactory.eINSTANCE.createList()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__ARRAY_ELEM_TYPE,
				 DataTypeFactory.eINSTANCE.createBoolean()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__ARRAY_ELEM_TYPE,
				 DataTypeFactory.eINSTANCE.createReal()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__ARRAY_ELEM_TYPE,
				 DataTypeFactory.eINSTANCE.createRangeInt()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__LENGTH,
				 ExpressionsFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__LENGTH,
				 ExpressionsFactory.eINSTANCE.createListExpr()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__LENGTH,
				 ExpressionsFactory.eINSTANCE.createArrayExpr()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__LENGTH,
				 ExpressionsFactory.eINSTANCE.createRecordExpr()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__LENGTH,
				 ExpressionsFactory.eINSTANCE.createIdentExpr()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__LENGTH,
				 ExpressionsFactory.eINSTANCE.createMathFunctions()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__LENGTH,
				 ExpressionsFactory.eINSTANCE.createRelationalExpr()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__LENGTH,
				 ExpressionsFactory.eINSTANCE.createArithExpr()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__LENGTH,
				 ExpressionsFactory.eINSTANCE.createPseudo_random_num_gen()));

		newChildDescriptors.add
			(createChildParameter
				(DataTypePackage.Literals.ARRAY__LENGTH,
				 ExpressionsFactory.eINSTANCE.createBooleanExpr()));
	}

}
