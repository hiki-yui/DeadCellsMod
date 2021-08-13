package deadCellsMod.cn.infinite.stsmod;

import basemod.abstracts.CustomReward;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class CardReward extends CustomReward {

    public CardReward(){
        super(new Texture((FileHandle) null),"",RewardType.CARD);
    }

    @Override
    public boolean claimReward() {
        return false;
    }
}
