package cc.xingyan.android.afc.photoutil;

import android.content.Context;
import android.net.Uri;

/**
 * Created by YuJiadeng on 2016/10/17.
 *
 */
public class CompressUtil implements LGImgCompressor.CompressListener {
    private String compressorPath;

    public CompressUtil(Context context, Uri mUri) {
        LGImgCompressor lgImgCompressor = LGImgCompressor.getInstance(context);

        lgImgCompressor.withListener(this).
                starCompress(mUri.toString(), 600, 800, 100);
    }

    @Override
    public void onCompressEnd(LGImgCompressor.CompressResult compressResult) {
        // Auto-generated method stub
        if (compressResult.getStatus() == LGImgCompressor.CompressResult.RESULT_ERROR){
            compressorPath = "";
        }else{
            compressorPath = compressResult.getOutPath();
        }


    }

    @Override
    public void onCompressStart() {

    }

    public String getCompressorPath(){
        return compressorPath;
    }
}
