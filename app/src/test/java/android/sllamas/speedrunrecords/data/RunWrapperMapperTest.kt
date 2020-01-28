package android.sllamas.speedrunrecords.data

import android.sllamas.speedrunrecords.data.remote.runs.*
import org.junit.Test

class RunWrapperMapperTest {

    @Test
    fun `should transform remote entity to domain entity correctly`() {
        val remoteRunEntity = getRunRemoteEntity()
        val domainRunEntity = remoteRunEntity.transformToDomain()

        assert(remoteRunEntity.times.timeInSeconds == domainRunEntity.time)
        assert(remoteRunEntity.players.first().id == domainRunEntity.playerId)
        assert(remoteRunEntity.players.first().name == domainRunEntity.playerName)
        assert(remoteRunEntity.videos.links.first().uri == domainRunEntity.videoUrl)
    }

    private fun getRunRemoteEntity(
        videos : VideosWrapperRemoteEntity = getVideosRemoteEntity(),
        players: List<PlayerRemoteEntity> = listOf(getPlayerRemoteEntity()),
        times: TimesRemoteEntity = getTimesRemoteEntity()
    ) = RunRemoteEntity(videos, players, times)

    private fun getVideosRemoteEntity(
        links: List<LinkRemoteEntity> = listOf(getLinkRemoteEntity())
    ) = VideosWrapperRemoteEntity(links)

    private fun getLinkRemoteEntity(uri: String = "uri") = LinkRemoteEntity(uri)

    private fun getPlayerRemoteEntity(
        id: String? = "id",
        name: String? = "name"
    ) = PlayerRemoteEntity(id, name)

    private fun getTimesRemoteEntity(time: Long = 2222) = TimesRemoteEntity(time)
}