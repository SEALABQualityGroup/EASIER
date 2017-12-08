/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions.provider;


import java.util.Collection;
import java.util.List;

import metamodel.mmaemilia.Expressions.BoolOpName;
import metamodel.mmaemilia.Expressions.BooleanExpr;
import metamodel.mmaemilia.Expressions.ExpressionsFactory;
import metamodel.mmaemilia.Expressions.ExpressionsPackage;

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
 * This is the item provider adapter for a {@link metamodel.mmaemilia.Expressions.BooleanExpr} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BooleanExprItemProvider
	extends ExpressionItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanExprItemProvider(AdapterFactory adapterFactory) {
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

			addOperatorPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Operator feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOperatorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_BooleanExpr_operator_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BooleanExpr_operator_feature", "_UI_BooleanExpr_type"),
				 ExpressionsPackage.Literals.BOOLEAN_EXPR__OPERATOR,
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
			childrenFeatures.add(ExpressionsPackage.Literals.BOOLEAN_EXPR__LEFT_EXPR_BOOL);
			childrenFeatures.add(ExpressionsPackage.Literals.BOOLEAN_EXPR__RIGHT_EXPR_BOOL);
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
	 * This returns BooleanExpr.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/BooleanExpr"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		BoolOpName labelValue = ((BooleanExpr)object).getOperator();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ?
			getString("_UI_BooleanExpr_type") :
			getString("_UI_BooleanExpr_type") + " " + label;
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

		switch (notification.getFeatureID(BooleanExpr.class)) {
			case ExpressionsPackage.BOOLEAN_EXPR__OPERATOR:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ExpressionsPackage.BOOLEAN_EXPR__LEFT_EXPR_BOOL:
			case ExpressionsPackage.BOOLEAN_EXPR__RIGHT_EXPR_BOOL:
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
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__LEFT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__LEFT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createListExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__LEFT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createArrayExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__LEFT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createRecordExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__LEFT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createIdentExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__LEFT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createMathFunctions()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__LEFT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createRelationalExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__LEFT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createArithExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__LEFT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createPseudo_random_num_gen()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__LEFT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createBooleanExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__RIGHT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__RIGHT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createListExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__RIGHT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createArrayExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__RIGHT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createRecordExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__RIGHT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createIdentExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__RIGHT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createMathFunctions()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__RIGHT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createRelationalExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__RIGHT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createArithExpr()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__RIGHT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createPseudo_random_num_gen()));

		newChildDescriptors.add
			(createChildParameter
				(ExpressionsPackage.Literals.BOOLEAN_EXPR__RIGHT_EXPR_BOOL,
				 ExpressionsFactory.eINSTANCE.createBooleanExpr()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == ExpressionsPackage.Literals.BOOLEAN_EXPR__LEFT_EXPR_BOOL ||
			childFeature == ExpressionsPackage.Literals.BOOLEAN_EXPR__RIGHT_EXPR_BOOL;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
