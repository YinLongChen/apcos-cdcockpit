package com.jinxin.platform.cdcockpit.utils;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;

import java.lang.reflect.Field;
import java.util.concurrent.Callable;

/**
 * 描述类的功能
 *
 * @author su
 * 2020-05-15 16:00
 */
@Slf4j
public class RTMPGrabberRecorderTask implements Callable<Boolean> {

    private String rtmpPath;

    private String rtspPath;

    private volatile boolean exit = false;

    public RTMPGrabberRecorderTask(String rtmpPath, String rtspPath) {
        this.rtmpPath = rtmpPath;
        this.rtspPath = rtspPath;
    }

    @Override
    public Boolean call() {
        try {
            int width = 640, height = 480;
            FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(rtspPath);
            grabber.setOption("rtsp_transport", "tcp"); // 使用tcp的方式，不然会丢包很严重
            // 一直报错的原因！！！就是因为是 2560 * 1440的太大了。。
            grabber.setImageWidth(width);
            grabber.setImageHeight(height);
            log.info("grabber start");
            grabber.start();
            //FrameRecorder recorder = FrameRecorder.createDefault(rtmpPath, 640,480,0);
            // 流媒体输出地址，分辨率（长，高），是否录制音频（0:不录制/1:录制）
            FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(rtmpPath, width, height, 0);
            recorder.setInterleaved(true);
            // 降低编码延时
            recorder.setVideoOption("tune", "zerolatency");
            // 提升编码速度
            recorder.setVideoOption("preset", "ultrafast");
            // 视频质量参数(详见 https://trac.ffmpeg.org/wiki/Encode/H.264)
            recorder.setVideoOption("crf", "28");
            // 分辨率
            recorder.setVideoBitrate(2000000);
            // 视频编码格式
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            // 视频格式
            recorder.setFormat("flv");
            // 视频帧率
            recorder.setFrameRate(15);
            recorder.setGopSize(60);
            recorder.setAudioOption("crf", "0");
            recorder.setAudioQuality(0);
            recorder.setAudioBitrate(192000);
            recorder.setSampleRate(44100);
            // 建议从grabber获取AudioChannels
            recorder.setAudioChannels(1);
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);

            avutil.av_log_set_level(avutil.AV_LOG_ERROR);
            recorder.start();

            // 解决音视频同步导致的延时问题
            Field field = recorder.getClass().getDeclaredField("oc");
            field.setAccessible(true);
            AVFormatContext oc = (AVFormatContext) field.get(recorder);
            oc.max_interleave_delta(100);
            //
            OpenCVFrameConverter.ToIplImage conveter = new OpenCVFrameConverter.ToIplImage();
            log.info("all start!!");
            int count = 0;
            while (!exit) {
                count++;
                Frame frame = grabber.grabImage();
                if (frame == null) {
                    break;
                }
                if (count % 100 == 0) {
                    log.info("count=" + count);
                }
                Thread.sleep(10);
                recorder.record(frame);
            }
            grabber.stop();
            grabber.release();
            recorder.stop();
            recorder.release();
            log.info("停止推流{}----> {}", rtspPath, rtmpPath);
        } catch (Exception e) {
            log.error("转换失败--{}", e);
        }
        return false;
    }

    public String getRtmpPath() {
        return rtmpPath;
    }

    public void setRtmpPath(String rtmpPath) {
        this.rtmpPath = rtmpPath;
    }

    public String getRtspPath() {
        return rtspPath;
    }

    public void setRtspPath(String rtspPath) {
        this.rtspPath = rtspPath;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
