//package hello;
//
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//
//class FormattedChangeListener implements PropertyChangeListener {
//    public void propertyChange(PropertyChangeEvent e) {
//        Object source = e.getSource();
//        //if (source == amountField) {
//        //    amount = ((Number)amountField.getValue()).doubleValue();
//        //
//        //}
//        if(source == App.numPeriodsField){
//            App.myInt = ((Number)e.getNewValue()).intValue();
//            System.out.println("app myint now: "+App.myInt);
//        }
//        if(source == App.textNameField){
//            App.myString2 = (e.getNewValue().toString());
//            System.out.println("app mystring now: "+App.myString2);
//        }
//        //re-compute payment and update field...
//    }
//}