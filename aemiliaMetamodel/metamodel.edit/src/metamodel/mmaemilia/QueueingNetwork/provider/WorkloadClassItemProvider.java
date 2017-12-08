/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.QueueingNetwork.provider;


import java.util.Collection;
import java.util.List;

import metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage;
import metamodel.mmaemilia.QueueingNetwork.WorkloadClass;

import metamodel.mmaemilia.provider.MmAEmiliaEditPlugin;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

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
 * This is the item provider adapter for a {@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class WorkloadClassItemProvider
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
	public WorkloadClassItemProvider(AdapterFactory adapterFactory) {
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

			addWorkloadIDPropertyDescriptor(object);
			addThroughputPropertyDescriptor(object);
			addResidenceTimePropertyDescriptor(object);
			addUtilizationPropertyDescriptor(object);
			addQueueLengthPropertyDescriptor(object);
			addServiceTimePropertyDescriptor(object);
			addEReference0PropertyDescriptor(object);
			addEReference1PropertyDescriptor(object);
			addTimeUnitsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Workload ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWorkloadIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_WorkloadClass_workloadID_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkloadClass_workloadID_feature", "_UI_WorkloadClass_type"),
				 QueueingNetworkPackage.Literals.WORKLOAD_CLASS__WORKLOAD_ID,
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
				 getString("_UI_WorkloadClass_throughput_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkloadClass_throughput_feature", "_UI_WorkloadClass_type"),
				 QueueingNetworkPackage.Literals.WORKLOAD_CLASS__THROUGHPUT,
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
				 getString("_UI_WorkloadClass_residenceTime_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkloadClass_residenceTime_feature", "_UI_WorkloadClass_type"),
				 QueueingNetworkPackage.Literals.WORKLOAD_CLASS__RESIDENCE_TIME,
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
				 getString("_UI_WorkloadClass_utilization_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkloadClass_utilization_feature", "_UI_WorkloadClass_type"),
				 QueueingNetworkPackage.Literals.WORKLOAD_CLASS__UTILIZATION,
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
				 getString("_UI_WorkloadClass_queueLength_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkloadClass_queueLength_feature", "_UI_WorkloadClass_type"),
				 QueueingNetworkPackage.Literals.WORKLOAD_CLASS__QUEUE_LENGTH,
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
				 getString("_UI_WorkloadClass_serviceTime_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkloadClass_serviceTime_feature", "_UI_WorkloadClass_type"),
				 QueueingNetworkPackage.Literals.WORKLOAD_CLASS__SERVICE_TIME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the EReference0 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEReference0PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_WorkloadClass_EReference0_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkloadClass_EReference0_feature", "_UI_WorkloadClass_type"),
				 QueueingNetworkPackage.Literals.WORKLOAD_CLASS__EREFERENCE0,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the EReference1 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEReference1PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_WorkloadClass_EReference1_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkloadClass_EReference1_feature", "_UI_WorkloadClass_type"),
				 QueueingNetworkPackage.Literals.WORKLOAD_CLASS__EREFERENCE1,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Time Units feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTimeUnitsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_WorkloadClass_timeUnits_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkloadClass_timeUnits_feature", "_UI_WorkloadClass_type"),
				 QueueingNetworkPackage.Literals.WORKLOAD_CLASS__TIME_UNITS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns WorkloadClass.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/WorkloadClass"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((WorkloadClass)object).getWorkloadID();
		return label == null || label.length() == 0 ?
			getString("_UI_WorkloadClass_type") :
			getString("_UI_WorkloadClass_type") + " " + label;
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

		switch (notification.getFeatureID(WorkloadClass.class)) {
			case QueueingNetworkPackage.WORKLOAD_CLASS__WORKLOAD_ID:
			case QueueingNetworkPackage.WORKLOAD_CLASS__THROUGHPUT:
			case QueueingNetworkPackage.WORKLOAD_CLASS__RESIDENCE_TIME:
			case QueueingNetworkPackage.WORKLOAD_CLASS__UTILIZATION:
			case QueueingNetworkPackage.WORKLOAD_CLASS__QUEUE_LENGTH:
			case QueueingNetworkPackage.WORKLOAD_CLASS__SERVICE_TIME:
			case QueueingNetworkPackage.WORKLOAD_CLASS__TIME_UNITS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
