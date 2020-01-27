package android.sllamas.speedrunrecords.data

import android.sllamas.speedrunrecords.data.remote.games.*
import org.junit.Test

class GameWrapperMappersTest {

    @Test
    fun `should transform remote entity to domain entity correctly`() {
        val remoteGamesEntity = getGameRemoteEntity()
        val domainGamesEntity = remoteGamesEntity.transformToDomain()

        assert(remoteGamesEntity.names.international == domainGamesEntity.name)
        assert(remoteGamesEntity.assets.coverSmall.uri == domainGamesEntity.logoUrl)
    }

    private fun getGameRemoteEntity(
        names: NameRemoteEntity = getNameRemoteEntity(),
        assets: AssetsRemoteEntity = getAssetsRemoteEntity()
    ) = GameRemoteEntity(names, assets)

    private fun getNameRemoteEntity(internationalName: String = "Some name") =
        NameRemoteEntity(internationalName)

    private fun getAssetsRemoteEntity(covers: CoversRemoteEntity = getCoversRemoteEntity()) =
        AssetsRemoteEntity(covers)

    private fun getCoversRemoteEntity(uri: String = "uri") = CoversRemoteEntity(uri)
}