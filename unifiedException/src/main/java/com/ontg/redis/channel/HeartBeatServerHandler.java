//package com.ontg.demo.redis.channel;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//
//public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {
//
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("server channelRead..");
//        System.out.println(ctx.channel().remoteAddress() + "->Server :" + msg.toString());
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//
// //当读取不到消息后，Netty触发ChannelReadCompleted().
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.flush();
//    }
//
//}
