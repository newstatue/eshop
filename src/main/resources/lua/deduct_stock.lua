-- KEYS[1] = 库存key   ARGV[1] = 购买数量
-- 返回: {code, before, deduct, after}
--   code: 1成功 0库存不足 -1key不存在
local cur = redis.call('get', KEYS[1])
if cur == false then
    return {-1, -1, -1, -1}              -- 库存key不存在
end

local before = tonumber(cur)
local deduct = tonumber(ARGV[1])

if before < deduct then
    return {0, before, deduct, before}   -- 库存不足，不扣减
end

local after = redis.call('decrby', KEYS[1], deduct)
return {1, before, deduct, after}        -- 成功