package parking;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class VipParkingStrategyTest {

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
        //given
        Car car = mock(Car.class);
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.isFull()).thenReturn(true);
        VipParkingStrategy spyVipParkingStrategy = spy(new VipParkingStrategy(null));
        doReturn(true).when(spyVipParkingStrategy).isAllowOverPark(car);

        //when
        spyVipParkingStrategy.park(Collections.singletonList(parkingLot), car);

        //then
        verify(spyVipParkingStrategy, times(0)).createNoSpaceReceipt(car);
        verify(spyVipParkingStrategy, times(1)).createReceipt(parkingLot, car);
    }


    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        //given
        Car car = mock(Car.class);
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.isFull()).thenReturn(true);
        VipParkingStrategy spyVipParkingStrategy = spy(new VipParkingStrategy(null));
        doReturn(false).when(spyVipParkingStrategy).isAllowOverPark(car);

        //when
        spyVipParkingStrategy.park(Collections.singletonList(parkingLot), car);

        //then
        verify(spyVipParkingStrategy, times(1)).createNoSpaceReceipt(car);
        verify(spyVipParkingStrategy, times(0)).createReceipt(parkingLot, car);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        Car car = new Car("A");
        CarDao carDao = mock(CarDao.class);
        VipParkingStrategy spyVipParkingStrategy = spy(new VipParkingStrategy(carDao));
        when(carDao.isVip(car.getName())).thenReturn(true);

        //when
        boolean allowOverPark = spyVipParkingStrategy.isAllowOverPark(car);

        //then
        assertTrue(allowOverPark);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
