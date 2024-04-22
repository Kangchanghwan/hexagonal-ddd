package org.example.redisdistributedlock.domain.point;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PointTest {


    @Test
    void 포인트가_증가한다(){
        Point _1000point = Point.valueOf(1000L);
        Point _100point = Point.valueOf(100L);

        Point expect_1100point = _1000point.plus(_100point);

        Assertions.assertEquals(expect_1100point.getAmount().longValue(), 1100L);
    }

    @Test
    void 포인트가_감소한다(){
        Point _1000point = Point.valueOf(1000L);
        Point _100point = Point.valueOf(100L);

        Point expect_900point = _1000point.minus(_100point);

        Assertions.assertEquals(expect_900point.getAmount().longValue(), 900L);
    }

    @Test
    void 포인트를_곱한다(){
        Point _1000point = Point.valueOf(1000L);

        Point expect_900point = _1000point.times(0.9);

        Assertions.assertEquals(expect_900point.getAmount().longValue(), 900L);
    }
    @Test
    void 내_포인트_보다_너의_포인트가_더_크거나_같다(){
        Point myPoint = Point.valueOf(1000L);
        Point _2000point = Point.valueOf(2000L);
        Point _1000point= Point.valueOf(1000L);

        boolean isGreater = _2000point.isGreaterThanOrEquals(myPoint);
        boolean isSame = _1000point.isGreaterThanOrEquals(myPoint);

        Assertions.assertTrue(isGreater);
        Assertions.assertTrue(isSame);
    }
    @Test
    void 내_포인트_보다_너의_포인트가_더_작다(){
        Point myPoint = Point.valueOf(1000L);
        Point _500point = Point.valueOf(500L);

        boolean isLess = _500point.isLessThan(myPoint);

        Assertions.assertTrue(isLess);
    }

}