package atm.model.bank;

import atm.constant.Constants;

public class AxisBank implements Bank {

    public String getName() {
        return Constants.AXIS_NAME;
    }

    public String getIFSC() {
        return Constants.AXIS_IFSC;
    }
}
