package training_college.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by ss14 on 2017/3/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DisCntHelperTest {

    @Autowired
    DisCntHelper disCntHelper;

    @Test
    public void getLevelByScore() throws Exception {

        int level = 1;
        int result= disCntHelper.getLevelByScore(400);
        assert result==level;
        level = 2;
        result= disCntHelper.getLevelByScore(500);
        assert result==level;
        level = 2;
        result= disCntHelper.getLevelByScore(800);
        assert result==level;
        level = 3;
        result= disCntHelper.getLevelByScore(1000);
        assert result==level;
        level = 3;
        result= disCntHelper.getLevelByScore(1300);
        assert result==level;
        level = 4;
        result= disCntHelper.getLevelByScore(2000);
        assert result==level;
        level = 4;
        result= disCntHelper.getLevelByScore(2100);
        assert result==level;
        level = 5;
        result= disCntHelper.getLevelByScore(5000);
        assert result==level;
        level = 5;
        result= disCntHelper.getLevelByScore(8000);
        assert result==level;

    }

    @Test
    public void getDisCntByLevel() throws Exception {

        double disCnt = 1;
        double result = disCntHelper.getDisCntByLevel(0);
        assert result==disCnt;

        disCnt = 1;
        result = disCntHelper.getDisCntByLevel(1);
        assert result==disCnt;

        disCnt = 0.9;
        result = disCntHelper.getDisCntByLevel(2);
        assert result==disCnt;

        disCnt = 0.8;
        result = disCntHelper.getDisCntByLevel(3);
        assert result==disCnt;

        disCnt = 0.7;
        result = disCntHelper.getDisCntByLevel(4);
        assert result==disCnt;

        disCnt = 0.6;
        result = disCntHelper.getDisCntByLevel(5);
        assert result==disCnt;

        disCnt = 0.6;
        result = disCntHelper.getDisCntByLevel(6);
        assert result==disCnt;

    }

}