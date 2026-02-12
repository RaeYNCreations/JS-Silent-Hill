package com.raeyncreations.silenthill.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public class SilentHillAxeItem extends SwordItem {
    public SilentHillAxeItem(Properties properties) {
        super(Tiers.IRON, properties.attributes(
                SwordItem.createAttributes(Tiers.IRON, 7, -2.4f)));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Add special effects when hitting entities
        // This replaces the "silenthill_axe_effect" function from the Bedrock addon
        return super.hurtEnemy(stack, target, attacker);
    }
}
