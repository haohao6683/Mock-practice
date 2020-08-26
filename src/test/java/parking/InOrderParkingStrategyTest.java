package parking;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

    private InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */
        //given
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("Pname");
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("Cname");

        //when
        Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot, car);

        //then
        assertEquals("Pname", receipt.getParkingLotName());
        assertEquals("Cname", receipt.getCarName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        //given
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("Cname");

        //when
        Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(car);

        //then
        assertEquals(ParkingStrategy.NO_PARKING_LOT, receipt.getParkingLotName());
        assertEquals("Cname", receipt.getCarName());

    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method.
	    Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        //given
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("car name");
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("Pname");
        when(parkingLot.isFull()).thenReturn(true);
        Receipt receipt = new Receipt();
        receipt.setCarName("car name");
        receipt.setParkingLotName(ParkingStrategy.NO_PARKING_LOT);
        InOrderParkingStrategy spyInOrderParkingStrategy = spy(new InOrderParkingStrategy());
        doReturn(receipt).when(spyInOrderParkingStrategy).createNoSpaceReceipt(car);

        //when
        spyInOrderParkingStrategy.park(Collections.singletonList(parkingLot), car);

        //then
        verify(parkingLot,
                times(1)).isFull();
        verify(spyInOrderParkingStrategy, times(1)).createNoSpaceReceipt(car);
        verify(spyInOrderParkingStrategy, times(0)).createReceipt(parkingLot, car);
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method.
        Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        //given
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("car name");
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("Pname");
        when(parkingLot.isFull()).thenReturn(false);
        InOrderParkingStrategy spyInOrderParkingStrategy = spy(new InOrderParkingStrategy());

        //when
        spyInOrderParkingStrategy.park(Collections.singletonList(parkingLot), car);

        //then
        verify(spyInOrderParkingStrategy, times(0)).createNoSpaceReceipt(car);
        verify(spyInOrderParkingStrategy, times(1)).createReceipt(parkingLot, car);
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("car name");
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("Pname");
        when(parkingLot.isFull()).thenReturn(true);
        InOrderParkingStrategy spyInOrderParkingStrategy = spy(new InOrderParkingStrategy());

        //when
        spyInOrderParkingStrategy.park(Collections.singletonList(parkingLot), car);

        //then
        verify(spyInOrderParkingStrategy, times(1)).createNoSpaceReceipt(car);
        verify(spyInOrderParkingStrategy, times(0)).createReceipt(parkingLot, car);
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
