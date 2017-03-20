package holiday_planner;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HolidayTest {

    private Holiday holiday;

    private Destination destinationX;
    private Destination destinationY;
    private Destination destinationZ;
    private Destination destinationV;
    private Destination destinationW;
    private Destination destinationU;

    @Before
    public void setUp() throws Exception {
        holiday = new Holiday();

        destinationX = new Destination("x");
        destinationY = new Destination("y");
        destinationZ = new Destination("z");
        destinationV = new Destination("v");
        destinationW = new Destination("w");
        destinationU = new Destination("u");
    }

    @Test(expected = NullPointerException.class)
    public void planRouteWithoutDestinations_throwsNullPointerException() {
        holiday.planRoute();
    }

    @Test
    public void oneDestinationAddedToHoliday_routeSizeIsOne() {
        holiday.addDestination(destinationX);
        holiday.planRoute();

        assertEquals(1, holiday.getRoute().size());
    }

    @Test
    public void multipleDestinations_orderless_firstAddedFirstReturned() {
        holiday.addDestination(destinationY);
        holiday.addDestination(destinationX);
        holiday.addDestination(destinationZ);
        holiday.planRoute();

        assertEquals(destinationY, holiday.getRoute().get(0));
    }

    @Test
    public void multipleDestinations_someOrdered() {
        destinationZ.setNextDestination(destinationY);
        holiday.addDestination(destinationX);
        holiday.addDestination(destinationZ);
        holiday.addDestination(destinationY);
        holiday.planRoute();

        assertTrue(holiday.getRoute().indexOf(destinationZ) < holiday.getRoute().indexOf(destinationY));
    }

    @Test
    public void nextDestinationIsSameAsStartingOne_oneDestinationAdded() {
        destinationU.setNextDestination(destinationU);
        holiday.addDestination(destinationU);
        holiday.planRoute();

        assertEquals(1, holiday.getRoute().size());
    }

}