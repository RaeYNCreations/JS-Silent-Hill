import { system, world } from '@minecraft/server';
import { decrementStack, getOppositeDirection, DirectionType, cardinalSides, randomFunction } from './utils/helper';
import { directionToVector3 } from './utils/math';

function durabilityOnChanged(item, player, isHitEntity = false) {
    let level = item.getComponent("minecraft:enchantable")?.getEnchantment("unbreaking")?.level;

    function durability() {
        let durability = item.getComponent("minecraft:durability");

        const t = Math.floor(Math.random() * 100);

        if (t < durability.getDamageChance()) {
            if (!isHitEntity) durability.damage += 1;
            if (durability.damage >= durability.maxDurability) {
                player.playSound("random.break");
                if (!isHitEntity) player.getComponent('equippable').setEquipment('Mainhand', undefined)
            } else {
                if (!isHitEntity) player.getComponent('equippable').setEquipment('Mainhand', item)
            }
        } else {
            if (!isHitEntity) return;
            durability.damage -= 1;
            if (!isHitEntity) player.getComponent('equippable').setEquipment('Mainhand', item);
        }
    }

    const t = Math.floor(Math.random() * 10)
    if (level === 1 && t > 8) return;
    else if (level === 2 && t > 6) return;
    else if (level === 3 && t > 4) return;
    else durability();
}

world.beforeEvents.worldInitialize.subscribe(initEvent => { initEvent.itemComponentRegistry.registerCustomComponent('jsart_silent_hil_axe:trigger', {
  onUse: e => { durabilityOnChanged(e.itemStack, e.source, false);
e.source.runCommand("function silenthill_axe_effect"); },
  onUseOn: e => { durabilityOnChanged(e.itemStack, e.source, false);
e.source.runCommand("function silenthill_axe_effect"); },
  onHitEntity: e => { durabilityOnChanged(e.itemStack, e.source, true); 
const functions = ["silenthill_axe_effect"];
          e.attackingEntity.runCommand(`function ${randomFunction(functions)}`);
           },
  onMineBlock: e => { durabilityOnChanged(e.itemStack, e.source, false); },
});
 });

