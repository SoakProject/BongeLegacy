# Bonge

Bonge is an emulation layer to run Bukkit plugins on the Sponge platform. 

**_Sponge API_**: 7.1.0

**_Bukkit_**: 1.13.2

**_Bonge_**: 1.0-SNAPSHOT

##Sponge 1.12.2 running 1.13 bukkit? What gives?
Yep, you read that right. Bukkit did some huge changes to make Bukkit more like minecraft's internal code which is a lot like what Sponge uses. Therefore the required Bukkit API to target needed to be 1.13 and as Sponge 1.14 is not out yet, Bonge targets 1.12. This will not be an ongoing thing, when 1.14 Sponge releases the Bukkit implementation will move to 1.14 and should stay in sync from then. These current builds are to give a good starting point for when Sponge does update. This is why there is very little development on Block and Item processing. 

##Compatibility list?
Yep, it does exist. https://docs.google.com/spreadsheets/d/1ZiDUcVUNO86vu0PpT4W2n0yEjbeNdvrkVjPkSxrMGbk 

Add to the list here.
https://forms.gle/qDfx1JW1PJeY2Xad6

## So … its pore?
Bonge is set out to achieve what Pore did, however no code is shared between the two projects - not deliberately. Pore closed down due to some very valid reasons that you can read about here https://caseif.blog/post.php?id=41 while some points are present in Bonge, here is why Bonge is still going forward. 

### ~~There are only two of us~~ There is only one of me
Yeah … Moving on

Sponge has accidently given this project a hand though. In the original post the Pore team take note that Minecraft's update cycle is too short for any real development for the team size they had. With minecraft announcing they wish to update minecraft multiple times per year, it  seemed like it would be even more impossible to keep up. However Sponge threw a bone stating Sponge will have LTS (Long Term Supported) releases, which Bonge will target with alpha releases for newer versions of minecraft allowing for stable releases of Bonge on LTS and unstable targeting the latest Sponge. 
### Sponge has become increasingly different to Bukkit
This was true back in 2016, however with the release of Minecraft 1.13, Bukkit has moved towards what Minecraft is, which Sponge is based upon due to the support of Forge. Therefore Bonge targets Bukkit’s 1.13 implementation. While not every part of Bukkit has moved towards Sponge, some has

### Many plugins probably won’t work
The explanation of NMS is very good on the link so please read that if you are unsure about NMS. While NMS still is a huge issue within the Bukkit eco system, the Bukkit team seems to be deterring NMS as shown by this post https://www.spigotmc.org/threads/spigot-bungeecord-1-15-2.414068/ found in “A note on NMS”. Bonge can not emulate NMS and will not, so hopfully with the deter, developers move more towards the Bukkit API so that Bonge can run it. 

### Even plugins that work probably won’t work with many Sponge mods
Yep this is still an issue, Bonge is designed for Sponge Vanilla and any none mod Sponge implementations such as LanternPowered. With that being said, there are parts of the Bukkit API that have been outright replaced with brand new code. While Forge mods still do not have support the example given on the page of “Material” has been replaced due to “LEGACY” materials so the entry of “OTHER” has been added which can be used for Forge blocks and items.

### Sponge is about New Beginnings
Sponge is about new beginnings, no doubt about that. However Bukkit 1.13+ forced a lot of developers to either update or abandon their plugins, making those plugins that did update a new start, hence why Bonge only supports plugins running 1.13+ api tags, while Bonge will not stop legacy plugins from running, they will not get support.   
