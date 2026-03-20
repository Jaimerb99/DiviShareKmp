package com.jrb.divishare.domain.usecase.groups

import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.model.Group
import com.jrb.divishare.domain.repo.GroupRepository

class GetMyGroupsUseCase(
    private val groupRepository: GroupRepository
) {
    suspend operator fun invoke(userId: String): DiviResult<List<Group>> {
        return groupRepository.getMyGroups(userId)
    }
}