package com.chen2059.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 连接 session
 *
 * @author 陈国震
 * @date 2022-07-22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionSession<T> {

    private String channelId;

    private String sessionId;

    private List<T> sessionPool;
}
