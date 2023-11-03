package com.openarknightsjvav.pojo.syncData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * ClassName: dngeon
 * Package: com.openarknightsjvav.pojo.syncData
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/3 13:17
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dungeon {
    private HashMap stages;
    private LinkedHashMap cowLevel;
}
