/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.provider;


import java.util.Collection;
import java.util.List;

import metamodel.mmaemilia.ArchiType;

import metamodel.mmaemilia.Headers.HeadersFactory;

import metamodel.mmaemilia.QueueingNetwork.QueueingNetworkFactory;
import metamodel.mmaemilia.mmaemiliaFactory;
import metamodel.mmaemilia.mmaemiliaPackage;

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
 * This is the item provider adapter for a {@link metamodel.mmaemilia.ArchiType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ArchiTypeItemProvider
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
	public ArchiTypeItemProvider(AdapterFactory adapterFactory) {
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

			addAtNamePropertyDescriptor(object);
			addThroughputPropertyDescriptor(object);
			addResidenceTimePropertyDescriptor(object);
			addUtilizationPropertyDescriptor(object);
			addQueueLengthPropertyDescriptor(object);
			addServiceTimePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the At Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAtNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ArchiType_atName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ArchiType_atName_feature", "_UI_ArchiType_type"),
				 mmaemiliaPackage.Literals.ARCHI_TYPE__AT_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Throughput feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addThroughputPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ArchiType_throughput_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ArchiType_throughput_feature", "_UI_ArchiType_type"),
				 mmaemiliaPackage.Literals.ARCHI_TYPE__THROUGHPUT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Residence Time feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResidenceTimePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ArchiType_residenceTime_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ArchiType_residenceTime_feature", "_UI_ArchiType_type"),
				 mmaemiliaPackage.Literals.ARCHI_TYPE__RESIDENCE_TIME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Utilization feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUtilizationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ArchiType_utilization_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ArchiType_utilization_feature", "_UI_ArchiType_type"),
				 mmaemiliaPackage.Literals.ARCHI_TYPE__UTILIZATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Queue Length feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addQueueLengthPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ArchiType_queueLength_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ArchiType_queueLength_feature", "_UI_ArchiType_type"),
				 mmaemiliaPackage.Literals.ARCHI_TYPE__QUEUE_LENGTH,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Service Time feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addServiceTimePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ArchiType_serviceTime_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ArchiType_serviceTime_feature", "_UI_ArchiType_type"),
				 mmaemiliaPackage.Literals.ARCHI_TYPE__SERVICE_TIME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
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
			childrenFeatures.add(mmaemiliaPackage.Literals.ARCHI_TYPE__AET_DECLARATION);
			childrenFeatures.add(mmaemiliaPackage.Literals.ARCHI_TYPE__AT_DECLARATION);
			childrenFeatures.add(mmaemiliaPackage.Literals.ARCHI_TYPE__HEADER);
			childrenFeatures.add(mmaemiliaPackage.Literals.ARCHI_TYPE__WORKLOADS);
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
	 * This returns ArchiType.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ArchiType"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ArchiType)object).getAtName();
		return label == null || label.length() == 0 ?
			getString("_UI_ArchiType_type") :
			getString("_UI_ArchiType_type") + " " + label;
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

		switch (notification.getFeatureID(ArchiType.class)) {
			case mmaemiliaPackage.ARCHI_TYPE__AT_NAME:
			case mmaemiliaPackage.ARCHI_TYPE__THROUGHPUT:
			case mmaemiliaPackage.ARCHI_TYPE__RESIDENCE_TIME:
			case mmaemiliaPackage.ARCHI_TYPE__UTILIZATION:
			case mmaemiliaPackage.ARCHI_TYPE__QUEUE_LENGTH:
			case mmaemiliaPackage.ARCHI_TYPE__SERVICE_TIME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case mmaemiliaPackage.ARCHI_TYPE__AET_DECLARATION:
			case mmaemiliaPackage.ARCHI_TYPE__AT_DECLARATION:
			case mmaemiliaPackage.ARCHI_TYPE__HEADER:
			case mmaemiliaPackage.ARCHI_TYPE__WORKLOADS:
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
				(mmaemiliaPackage.Literals.ARCHI_TYPE__AET_DECLARATION,
				 mmaemiliaFactory.eINSTANCE.createArchiElemTypes()));

		newChildDescriptors.add
			(createChildParameter
				(mmaemiliaPackage.Literals.ARCHI_TYPE__AT_DECLARATION,
				 mmaemiliaFactory.eINSTANCE.createArchiTopology()));

		newChildDescriptors.add
			(createChildParameter
				(mmaemiliaPackage.Literals.ARCHI_TYPE__HEADER,
				 HeadersFactory.eINSTANCE.createAT_Header()));

		newChildDescriptors.add
			(createChildParameter
				(mmaemiliaPackage.Literals.ARCHI_TYPE__WORKLOADS,
				 QueueingNetworkFactory.eINSTANCE.createWorkload()));
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
