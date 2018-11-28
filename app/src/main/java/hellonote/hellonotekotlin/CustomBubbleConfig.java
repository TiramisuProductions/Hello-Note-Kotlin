package hellonote.hellonotekotlin;

import android.content.Context;
import android.graphics.Point;
import com.flipkart.chatheads.config.ChatHeadDefaultConfig;
import com.flipkart.chatheads.utils.ChatHeadUtils;

public class CustomBubbleConfig extends ChatHeadDefaultConfig {
    public CustomBubbleConfig(Context context, int xPosition, int yPosition) {
        super(context);
        setHeadHorizontalSpacing(ChatHeadUtils.dpToPx(context, -2));
        setHeadVerticalSpacing(ChatHeadUtils.dpToPx(context, 2));
        setHeadWidth(ChatHeadUtils.dpToPx(context, 50));
        setHeadHeight(ChatHeadUtils.dpToPx(context, 50));
        setInitialPosition(new Point(xPosition, yPosition));
        setCloseButtonHeight(ChatHeadUtils.dpToPx(context, 50));
        setCloseButtonWidth(ChatHeadUtils.dpToPx(context, 50));
        setCloseButtonBottomMargin(ChatHeadUtils.dpToPx(context, 100));
        setCircularRingWidth(ChatHeadUtils.dpToPx(context, 53));
        setCircularRingHeight(ChatHeadUtils.dpToPx(context, 53));
    }

    @Override
    public int getMaxChatHeads(int maxWidth, int maxHeight) {
        return (int) Math.floor(maxWidth / (getHeadWidth() + getHeadHorizontalSpacing(maxWidth, maxHeight))) - 1;
    }
}
