package atm.model.bank;

import atm.constant.Constants;

public class AxisBank implements Bank {

    @Override
    public String getName() {
        return Constants.AXIS_NAME;
    }

    @Override
    public String getIFSC() {
        return Constants.AXIS_IFSC;
    }
}
