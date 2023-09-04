package deadCellsMod.cn.infinite.stsmod.cards;

import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

public class Roll_ButNot extends Roll {
    public Roll_ButNot(){
        super("deadCells:Roll_ButNot","翻滚",CardRarity.SPECIAL, AbstractDeadCellsEnum.DEAD_CELLS);
        this.setBackgroundTexture(DeadCellsModInitializer.GRAY_SKILL_CARD, DeadCellsModInitializer.GRAY_SKILL_CARD_PORTRAIT);
    }

    /*@Override
    public AbstractCard makeCopy() {
        AbstractCard card = new Roll_ButNot();
        if (this.upgraded){
            card.upgrade();
        }
        return card;
    }*/
}
