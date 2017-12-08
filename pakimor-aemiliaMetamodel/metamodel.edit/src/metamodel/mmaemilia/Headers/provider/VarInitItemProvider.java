/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers.provider;


import java.util.Collection;
import java.util.List;

import metamodel.mmaemilia.DataType.DataTypeFactory;

import metamodel.mmaemilia.Expressions.ExpressionsFactory;

import metamodel.mmaemilia.Headers.HeadersPackage;
import metamodel.mmaemilia.Headers.VarInit;

import metamodel.mmaemilia.provider.MmAEmiliaEditPlugin;

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
 * This is the item provider adapter for a {@link metamodel.mmaemilia.Headers.VarInit} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class VarInitItemProvider
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
	public VarInitItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_VarInit_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_VarInit_name_feature", "_UI_VarInit_type"),
				 HeadersPackage.Literals.VAR_INIT__NAME,
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
			childrenFeatures.add(HeadersPackage.Literals.VAR_INIT__INIT_VAR_EXPR);
			childrenFeatures.add(HeadersPackage.Literals.VAR_INIT__INIT_VAR_TYPE);
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
	 * This returns VarInit.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/VarInit"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((VarInit)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_VarInit_type") :
			getString("_UI_VarInit_type") + " " + label;
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

		switch (notification.getFeatureID(VarInit.class)) {
			case HeadersPackage.VAR_INIT__NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case HeadersPackage.VAR_INIT__INIT_VAR_EXPR:
			case HeadersPackage.VAR_INIT__INIT_VAR_TYPE:
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
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_EXPR,
				 ExpressionsFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_EXPR,
				 ExpressionsFactory.eINSTANCE.createListExpr()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_EXPR,
				 ExpressionsFactory.eINSTANCE.createArrayExpr()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_EXPR,
				 ExpressionsFactory.eINSTANCE.createRecordExpr()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_EXPR,
				 ExpressionsFactory.eINSTANCE.createIdentExpr()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_EXPR,
				 ExpressionsFactory.eINSTANCE.createMathFunctions()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_EXPR,
				 ExpressionsFactory.eINSTANCE.createRelationalExpr()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_EXPR,
				 ExpressionsFactory.eINSTANCE.createArithExpr()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_EXPR,
				 ExpressionsFactory.eINSTANCE.createPseudo_random_num_gen()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_EXPR,
				 ExpressionsFactory.eINSTANCE.createBooleanExpr()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_TYPE,
				 DataTypeFactory.eINSTANCE.createNormal()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_TYPE,
				 DataTypeFactory.eINSTANCE.createInteger()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_TYPE,
				 DataTypeFactory.eINSTANCE.createRecord()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_TYPE,
				 DataTypeFactory.eINSTANCE.createArray()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_TYPE,
				 DataTypeFactory.eINSTANCE.createList()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_TYPE,
				 DataTypeFactory.eINSTANCE.createBoolean()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_TYPE,
				 DataTypeFactory.eINSTANCE.createReal()));

		newChildDescriptors.add
			(createChildParameter
				(HeadersPackage.Literals.VAR_INIT__INIT_VAR_TYPE,
				 DataTypeFactory.eINSTANCE.createRangeInt()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return MmAEmiliaEditPlugin.INSTANCE;
	}

}
