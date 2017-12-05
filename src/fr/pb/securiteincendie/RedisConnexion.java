/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pb.securiteincendie;

import redis.clients.jedis.Jedis;

public class RedisConnexion {

    /**
     *
     * @return
     */
    public static Jedis getConnection() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("localhost");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jedis;
    } /// seConnecter

    /**
     *
     * @param jedis
     * @return
     */
    public static boolean closeConnection(Jedis jedis) {
        boolean lbOK = true;
        try {
            if (jedis != null) {
                jedis.disconnect();
                //jedis.close();
            }
        } catch (Exception e) {
            lbOK = false;
            System.out.println(e.getMessage());
        }
        return lbOK;
    } /// closeConnection

} /// class

