# Bonge

Bonge is an emulation layer to run Bukkit plugins on the Sponge platform. 
<br><br>
**_Sponge API_**: 8.0.0 <br>
<br>
**_Bukkit_**: 1.14.4<br>
<br>
**_Bonge_**: 2.0-SNAPSHOT <br>

## Compatibility list?
Yep, it does exist, please note this list is for server mode only. https://docs.google.com/spreadsheets/d/1ZiDUcVUNO86vu0PpT4W2n0yEjbeNdvrkVjPkSxrMGbk 

Add to the list here.
https://forms.gle/qDfx1JW1PJeY2Xad6

## Client (Single player / LAN) mode
Client mode is supported, however, the compatibility with Bukkit plugins is not as good as all Bukkit plugins assume they are running on a server and therefore make calls that target a server environment, however these calls do not exist (or won't later) on the client. <br><br>
In a attempt to keep compatibility high, if the Bukkit plugin attempts to make a call to the server which does not exsist on the client, Bonge will attempt to prevent crashes from occurring and give a next best Sponge call. For example, if a Bukkit plugin attempts to get a player's IP, Bonge will return "LocalHost" which is the IP that the server (single player or multiplayer) is running on, or attempt to get the local IPV4 address for LAN use.

## So … its pore?
Bonge is set out to achieve what Pore did, however no code is shared between the two projects - not deliberately. Pore closed down due to some very valid reasons that you can read about here https://caseif.blog/post.php?id=41 while some points are present in Bonge, here is why Bonge is still going forward. 

### ~~There are only two of us~~ There is only one of me
Yeah … Moving on

Sponge has accidentally given this project a hand though. In the original post the Pore team take note that Minecraft's update cycle is too short for any real development for the team size they had. With minecraft announcing they wish to update minecraft multiple times per year, it  seemed like it would be even more impossible to keep up. However Sponge threw a bone stating Sponge will have LTS (Long Term Supported) releases, which Bonge will target with alpha releases for newer versions of minecraft allowing for stable releases of Bonge on LTS and unstable targeting the latest Sponge. 
### Sponge has become increasingly different to Bukkit
This was true back in 2016, however with the release of Minecraft 1.13, Bukkit has moved towards what Minecraft is, which Sponge is based upon due to the support of Forge. Therefore, Bonge targets Bukkit’s 1.13 implementation. While not every part of Bukkit has moved towards Sponge, some has.

### Many plugins probably won’t work
The explanation of NMS is very good on the link so please read that if you are unsure about NMS. While NMS still is a huge issue within the Bukkit eco system, the Bukkit team seems to be deterring NMS as shown by this post https://www.spigotmc.org/threads/spigot-bungeecord-1-15-2.414068/ found in “A note on NMS”. Bonge cannot emulate NMS and will not, so hopefully with the deter, developers move more towards the Bukkit API so that Bonge can run it. 

### Even plugins that work probably won’t work with many Sponge mods
Yep this is still an issue, Bonge is designed for Sponge Vanilla and any none mod Sponge implementations such as LanternPowered. With that being said, there are parts of the Bukkit API that have been outright replaced with brand new code. While Forge mods still do not have support the example given on the page of “Material” has been replaced due to “LEGACY” materials so the entry of “OTHER” has been added which can be used for Forge blocks and items.

### Sponge is about New Beginnings
Sponge is about new beginnings, no doubt about that. However, Bukkit 1.13+ forced a lot of developers to either update or abandon their plugins, making those plugins that did update a new start, hence why Bonge only supports plugins running 1.13+ api tags, while Bonge will not stop legacy plugins from running, they will not get support.   
